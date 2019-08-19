package mbronshteyn.lab4sentence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class User {

  @NotNull( message = "First Name can not be null" )
  @Size( min=2, message = "First Name can not be less than two characters")
  public String firstName;

  @NotNull( message = "Last Name can not be null" )
  @Size( min=2, message = "Last Name can not be less than two characters")
  public String lastName;

  @NotNull( message = "Password can not be null" )
  @Size( min=2, max = 8, message = "Password can not be less than 2 characters and more than 8 characters")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public String password;

  @NotNull( message = "Email can not be null" )
  @Email
  public String email;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
