package com.perry.ClassicNotes.data.dto.request;

import com.perry.ClassicNotes.utils.validators.UserDetailsValidators;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordRequest {
    @NotBlank(message = "This field must not be empty")
    private String email;

    @NotBlank(message = "This field must not be empty")
    private String token;
    @NotBlank(message = "This field must not be empty")
    private String password;
    @NotBlank(message = "This field must not be empty")
    private String confirmPassword;

    public String getPassword() {
        if(UserDetailsValidators.isValidPassword(password))
            return BCrypt.hashpw(password, BCrypt.gensalt());
        else
            throw new RuntimeException("password must contain at least one " +
                    "capital letter, small letter and special characters");
    }
}
