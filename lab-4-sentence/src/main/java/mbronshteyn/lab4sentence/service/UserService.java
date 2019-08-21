package mbronshteyn.lab4sentence.service;

import mbronshteyn.lab4sentence.model.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
  UserDTO createUser( UserDTO userDTO );
}
