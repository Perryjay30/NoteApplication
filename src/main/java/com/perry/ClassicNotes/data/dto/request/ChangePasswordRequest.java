package com.perry.ClassicNotes.data.dto.request;

import com.perry.ClassicNotes.utils.validators.UserDetailsValidators;

import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "This field must not be empty")
    private String email;
    @NotBlank(message = "This field must not be empty")
    private String oldPassword;
    @NotBlank(message = "This field must not be empty")
    private String newPassword;


    public String getNewPassword() {
        if(UserDetailsValidators.isValidPassword(newPassword))
            return BCrypt.hashpw(newPassword, BCrypt.gensalt());
        else
            throw new RuntimeException("password must contain at least one " +
                    "capital letter, small letter and special characters");
    }
}
