package mbronshteyn.lab4sentence.model.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mbronshteyn.lab4sentence.model.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserDTO extends User implements Serializable {

  private String userId;

  @JsonIgnore
  private String ecnryptedPassword;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEcnryptedPassword() {
    return ecnryptedPassword;
  }

  public void setEcnryptedPassword(String ecnryptedPassword) {
    this.ecnryptedPassword = ecnryptedPassword;
  }
}
