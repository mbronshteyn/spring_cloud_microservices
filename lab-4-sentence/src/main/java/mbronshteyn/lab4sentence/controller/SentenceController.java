package mbronshteyn.lab4sentence.controller;

import mbronshteyn.lab4sentence.data.UserRepository;
import mbronshteyn.lab4sentence.model.CustomException;
import mbronshteyn.lab4sentence.model.User;
import mbronshteyn.lab4sentence.model.shared.UserDTO;
import mbronshteyn.lab4sentence.repo.*;
import mbronshteyn.lab4sentence.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RefreshScope
public class SentenceController {

  Logger logger = LoggerFactory.getLogger( this.getClass() );

  @Autowired
  SubjectRepo subjectRepo;

  @Autowired
  VerbRepo verbRepo;

  @Autowired
  ArticleRepo articleRepo;

  @Autowired
  AdjectiveRepo adjectiveRepo;

  @Autowired
  NounRepo nounRepo;

  @Autowired
  UserService userService;

  @Autowired
  ModelMapper modelMapper;

  @Value("${server.port}")
  Long port;

  @Value("${token.secret}")
  String token;

  @GetMapping( "/port" )
  public @ResponseBody Long getPort( @RequestHeader Map<String, String> headers){
    logger.info( "=============================================" );
    headers.forEach( ( key, value ) ->  logger.info( String.format( "Header Name: %s, Value: %s", key, value )));
    logger.info( "=============================================" );
    return port;
  }

  @GetMapping( "/token" )
  public @ResponseBody String getToken(){
      return token;
  }

  @GetMapping( "/exception" )
  public @ResponseBody Long getException() throws Exception {
    throw new NullPointerException("test");
  }

  @GetMapping( "/custom-exception" )
  public @ResponseBody Long getCustomException() throws Exception {
    throw new CustomException();
  }

  @PostMapping( "/users" )
  public @ResponseBody
  ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user ){
    UserDTO userDTO = userService.createUser(modelMapper.map(user, UserDTO.class));
   return new ResponseEntity( userDTO, HttpStatus.CREATED );
  }

  @GetMapping("/sentence")
  public @ResponseBody String getSentence() {
      String msg = subjectRepo.getItem() + " "
        + verbRepo.getItem() + " "
        + articleRepo.getItem() + " "
        + adjectiveRepo.getItem() + " "
        + nounRepo.getItem() + ".\n" ;

      logger.info( "SentenceController: " + msg );
      return msg;
  }
}
