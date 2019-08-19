package mbronshteyn.lab4sentence.service;

import mbronshteyn.lab4sentence.data.UserEntity;
import mbronshteyn.lab4sentence.data.UserRepository;
import mbronshteyn.lab4sentence.model.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  public ModelMapper modelMapper;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    userDTO.setUserId( UUID.randomUUID().toString() );

    UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
    userEntity.setEcnryptedPassword( bCryptPasswordEncoder.encode( userDTO.getPassword()) );
    // configure email to be unique
    userEntity.setEmail( (int)(Math.random()*100) + userEntity.getEmail());

    UserEntity savedEntity = userRepository.save(userEntity);

    UserDTO dto = modelMapper.map(savedEntity, UserDTO.class);

    return dto;
  }
}
