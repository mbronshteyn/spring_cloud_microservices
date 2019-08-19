package mbronshteyn.lab4sentence.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name="users" )
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue
  long id;

  @Column ( nullable = false, length = 50 )
  String firstName;

  @Column ( nullable = false, length = 50 )
  String lastName;

  @Column ( nullable = false, length = 150, unique = true )
  String email;

  @Column ( nullable = false, unique = true )
  String userId;

  @Column ( nullable = false, unique = true )
  String ecnryptedPassword;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEcnryptedPassword() {
    return ecnryptedPassword;
  }

  public void setEcnryptedPassword(String ecnryptedPassword) {
    this.ecnryptedPassword = ecnryptedPassword;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
