package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.dto.request.*;
import com.perry.ClassicNotes.data.dto.response.LoginResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.UserRegistrationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void testThatCustomerAccountHasBeenCreated() {
        VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
        verifyOtpRequest.setToken("5435");
        verifyOtpRequest.setEmail("stonbries@yahoo.com");
        UserRegistrationResponse registrationResponse =
                userService.createAccount(verifyOtpRequest);
        assertEquals("Registration successful", registrationResponse.getMessage());
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail(registerFirstUserRequest.getEmail());
//        Optional<User> registeredUser =
//                userRepository.findUserByEmail(loginRequest.getEmail());
//        assertNotNull(registeredUser);
//        loginRequest.setPassword(registerFirstUserRequest.getPassword());
//        LoginResponse loginResponse = userService.login(loginRequest);
//        assertEquals("successful login", loginResponse.getMessage());
        loginRequest.setEmail("stonbries@yahoo.com");
        loginRequest.setPassword("Juststones&11");
        LoginResponse loginResponse = userService.login(loginRequest);
        assertEquals("successful login", loginResponse.getMessage());
    }

    @Test
    void testThatUserCanChangePassword() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setEmail("stonbries@yahoo.com");
        changePasswordRequest.setOldPassword("Juststones&11");
        changePasswordRequest.setNewPassword("Britianna&00");
        Response resp = userService.changePassword(changePasswordRequest);
        assertEquals("Your password has been successfully changed", resp.getMessage());
    }

    @Test
    void testThatForgotPasswordMethodWorks() throws MessagingException {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("stonbries@yahoo.com");
        var response = userService.forgotPassword(forgotPasswordRequest);
        assertEquals("Token successfully sent to your email. Please check.", response);
    }

    @Test
    void testThatPasswordCanBeResetAfterForgotten() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken("8480");
        resetPasswordRequest.setEmail("stonbries@yahoo.com");
        resetPasswordRequest.setPassword("Thewhitecalf#89");
        resetPasswordRequest.setConfirmPassword("Thewhitecalf#89");
        Response answer = userService.resetPassword(resetPasswordRequest);
        assertEquals("Your password has been reset successfully", answer.getMessage());
    }


    @Test
    void updateUser() {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setFirstName("Alphonso");
        updateRequest.setLastName("Davies");
        updateRequest.setPhone("+234 (906) 704-7496");
        Response response = userService.updateUser(1, updateRequest);
        assertEquals("user details update successfully", response.getMessage());
    }

    @Test
    void deleteUser() {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setPassword("Thewhitecalf#89");
        Response response = userService.deleteUser(1, deleteRequest);
        System.out.println(response);
        assertEquals("User deleted", response.getMessage());
    }

}