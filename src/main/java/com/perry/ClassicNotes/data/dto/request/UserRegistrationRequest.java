package com.perry.ClassicNotes.data.dto.request;

import com.perry.ClassicNotes.utils.validators.UserDetailsValidators;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationRequest {
   @NotBlank(message = "This field must not be empty")
   private String firstName;
   @NotBlank(message = "This field must not be empty")
   private String lastName;
   @NotBlank(message = "This field must not be empty")
   private String email;
   @NotBlank(message = "This field must not be empty")
   private String password;

   public String getPassword() {
      if(UserDetailsValidators.isValidPassword(password))
         return BCrypt.hashpw(password, BCrypt.gensalt());
      else
         throw new RuntimeException("password must contain at least one " +
                 "capital letter, small letter and special characters");
   }
}
