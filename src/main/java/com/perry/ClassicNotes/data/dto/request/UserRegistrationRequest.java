package com.perry.ClassicNotes.data.dto.request;

import com.perry.ClassicNotes.validators.UserDetailsValidators;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

@Data
public class UserRegistrationRequest {
   private String email;
   private String password;
   private String phoneNumber;

   public String getPassword() {
      if(UserDetailsValidators.isValidPassword(password))
         return BCrypt.hashpw(password, BCrypt.gensalt());
      else
         throw new RuntimeException("password must contain at least one " +
                 "capital letter, small letter and special characters");
   }
}
