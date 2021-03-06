package mbronshteyn.lab4sentence.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import mbronshteyn.lab4sentence.model.LoginRequest;
import mbronshteyn.lab4sentence.model.shared.UserDTO;
import mbronshteyn.lab4sentence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Autowired
  private UserService userService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Value("${token.expiration_time}")
  String tokenExpirationTime;

  @Value("${token.secret}")
  String tokenSecret;

  @Autowired
  @Override
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          creds.getEmail(),
          creds.getPassword(),
          new ArrayList<>()
        )
      );

      return authentication;

    } catch (IOException e) {
      throw new RuntimeException( e );
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication auth ) throws IOException, ServletException {
    String userName = ((User)auth.getPrincipal()).getUsername();
    UserDTO userDTO = userService.getUserByEmail( userName );

    String token = Jwts.builder()
      .setSubject( userDTO.getUserId() )
      .setExpiration( new Date( System.currentTimeMillis() + Long.parseLong( tokenExpirationTime )))
      .signWith( SignatureAlgorithm.HS512, tokenSecret )
      .compact();

    response.addHeader( "token", token );
    response.addHeader( "userId", userDTO.getUserId() );

  }
}
