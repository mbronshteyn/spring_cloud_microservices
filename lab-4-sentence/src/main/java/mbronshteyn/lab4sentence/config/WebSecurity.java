package mbronshteyn.lab4sentence.config;

import mbronshteyn.lab4sentence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Value( "${gateway.ip}" )
  public String gatewayIp;

  Environment environment;
  UserService userService;
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public WebSecurity (Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
    this.environment = environment;
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests()
      .antMatchers( "/port/**").permitAll()
      .antMatchers("/sentence/**").permitAll()
      .antMatchers("/**").hasIpAddress( gatewayIp )
      .and().addFilter( getAuthenticationFilter() );

    http.headers().frameOptions().disable();
  }

  public AuthenticationFilter getAuthenticationFilter() throws Exception{
    AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    authenticationFilter.setAuthenticationManager( authenticationManager() );
    return authenticationFilter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService( userService ).passwordEncoder( bCryptPasswordEncoder );
  }
}
