package mbronshteyn.lab4sentence.service;

import mbronshteyn.lab4sentence.data.UserEntity;
import mbronshteyn.lab4sentence.data.UserRepository;
import mbronshteyn.lab4sentence.model.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService  {

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
    userEntity.setEmail( userEntity.getEmail() );

    UserEntity savedEntity = userRepository.save(userEntity);

    UserDTO dto = modelMapper.map(savedEntity, UserDTO.class);

    return dto;
  }

  @Override
  public UserDTO getUserByEmail(String email) {

    UserEntity userEntity = userRepository.findByEmail(email);

    if( userEntity == null ) throw new UsernameNotFoundException( email );

    return modelMapper.map( userEntity, UserDTO.class);
  }

  /**
   * Username is an email
   *
   * @param email
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email);

    if( userEntity == null ) throw new UsernameNotFoundException( email );

    return new User( email, userEntity.getEcnryptedPassword(),
      true, true, true, true, new ArrayList<>());
  }
}
