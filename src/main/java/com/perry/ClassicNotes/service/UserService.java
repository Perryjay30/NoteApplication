package com.perry.ClassicNotes.service;


import com.perry.ClassicNotes.data.dto.request.*;
import com.perry.ClassicNotes.data.models.User;
import com.perry.ClassicNotes.data.dto.response.LoginResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.UserRegistrationResponse;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

   UserRegistrationResponse createAccount(VerifyOtpRequest verifyOtpRequest);

   void verifyOTP(VerifyOtpRequest verifyOtpRequest);

   String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws MessagingException;

   Response resetPassword(ResetPasswordRequest resetPasswordRequest);

   Response changePassword(ChangePasswordRequest changePasswordRequest);

   String sendOTP(SendOtpRequest sendOtpRequest);

   //   UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest);
   LoginResponse login(LoginRequest loginRequest);
   Response deleteUser(int id, DeleteRequest deleteRequest);
//   List<User> getAllUserFromDb();
   Response updateUser(int id, UpdateRequest updateRequest);

   Response deleteAllUsers();
}
