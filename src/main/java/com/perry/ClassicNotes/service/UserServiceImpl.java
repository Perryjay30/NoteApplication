package com.perry.ClassicNotes.service;

;
import com.perry.ClassicNotes.data.dto.request.*;
import com.perry.ClassicNotes.data.models.OTPToken;
import com.perry.ClassicNotes.data.models.User;
import com.perry.ClassicNotes.data.repository.OtpTokenRepository;
import com.perry.ClassicNotes.data.repository.UserRepository;
import com.perry.ClassicNotes.data.dto.response.LoginResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.UserRegistrationResponse;
import com.perry.ClassicNotes.utils.EmailService;
import com.perry.ClassicNotes.utils.Token;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.perry.ClassicNotes.data.models.Status.VERIFIED;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final OtpTokenRepository otpTokenRepository;

    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OtpTokenRepository otpTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.otpTokenRepository = otpTokenRepository;
        this.emailService = emailService;
    }


    private UserRegistrationResponse newUserRegistrationResponse(User savedUser) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setMessage("Registration successful");
        response.setStatusCode(201);
        response.setId(savedUser.getId());
        return response;
    }


    @Override
    public UserRegistrationResponse createAccount(VerifyOtpRequest verifyOtpRequest) {
        verifyOTP(verifyOtpRequest);
        var savedUser = userRepository.findUserByEmail(verifyOtpRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User does not exists"));
        savedUser.setStatus(VERIFIED);
        userRepository.save(savedUser);
        return newUserRegistrationResponse(savedUser);
    }

    @Override
    public void verifyOTP(VerifyOtpRequest verifyOtpRequest) {
        OTPToken foundToken = otpTokenRepository.findByToken(verifyOtpRequest.getToken())
                .orElseThrow(() -> new RuntimeException("Token doesn't exist"));
        if(foundToken.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Token has expired");
        if(foundToken.getVerifiedAt() != null)
            throw new RuntimeException("Token has been used");
        if(!Objects.equals(verifyOtpRequest.getToken(), foundToken.getToken()))
            throw new RuntimeException("Incorrect token");
        otpTokenRepository.setVerifiedAt(LocalDateTime.now(), verifyOtpRequest.getToken());
//        var token = otpTokenRepository.findByToken(foundToken.getToken())
//                .orElseThrow(()->new RuntimeException("token not found"));
//        token.setVerifiedAt(LocalDateTime.now());
//        otpTokenRepository.save(token);
    }

    @Override
    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws MessagingException {
        User forgotUser = userRepository.findUserByEmail(forgotPasswordRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("This email is not registered"));
        String token = Token.generateToken(4);
        OTPToken otpToken = new OTPToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), forgotUser);
        otpTokenRepository.save(otpToken);
        emailService.sendEmail(forgotPasswordRequest.getEmail(), forgotUser.getFirstName(), token);
        return "Token successfully sent to your email. Please check.";
    }

    @Override
    public Response resetPassword(ResetPasswordRequest resetPasswordRequest) {
        verifyOtpForResetPassword(resetPasswordRequest);
        User foundUser = userRepository.findUserByEmail(resetPasswordRequest.getEmail()).get();
        foundUser.setPassword(resetPasswordRequest.getPassword());
        if(BCrypt.checkpw(resetPasswordRequest.getConfirmPassword(), resetPasswordRequest.getPassword())) {
            userRepository.save(foundUser);
            return new Response("Your password has been reset successfully");
        } else {
            throw new IllegalStateException("Password does not match");
        }
    }

    private void verifyOtpForResetPassword(ResetPasswordRequest resetPasswordRequest) {
        var foundToken = otpTokenRepository.findByToken(resetPasswordRequest.getToken())
                .orElseThrow(() -> new RuntimeException("Token doesn't exist"));
        if(foundToken.getVerifiedAt() != null)
            throw new RuntimeException("Token has been used");
        if(foundToken.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Token has expired");
        if(!Objects.equals(resetPasswordRequest.getToken(), foundToken.getToken()))
            throw new RuntimeException("Incorrect token");
        otpTokenRepository.setVerifiedAt(LocalDateTime.now(), resetPasswordRequest.getToken());
//        var token = otpTokenRepository.findByToken(foundToken.getToken())
//                .orElseThrow(()->new RuntimeException("token not found"));
//        token.setVerifiedAt(LocalDateTime.now());
//        otpTokenRepository.save(token);
    }

    @Override
    public Response changePassword(ChangePasswordRequest changePasswordRequest) {
        User verifiedUser = userRepository.findUserByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("customer isn't registered"));
        if(BCrypt.checkpw(changePasswordRequest.getOldPassword(), verifiedUser.getPassword()))
            verifiedUser.setPassword(changePasswordRequest.getNewPassword());
        userRepository.save(verifiedUser);
        return new Response("Your password has been successfully changed");
    }

    @Override
    public String sendOTP(SendOtpRequest sendOtpRequest) {
        User savedUser = userRepository.findUserByEmail(sendOtpRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));
        return generateOtpToken(sendOtpRequest, savedUser);
    }

    private String generateOtpToken(SendOtpRequest sendOtpRequest, User savedUser) {
        String token = Token.generateToken(4);
        OTPToken otpToken = new OTPToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), savedUser);
        otpTokenRepository.save(otpToken);
        emailService.send(sendOtpRequest.getEmail(), emailService.buildEmail(savedUser.getFirstName(), token));
        return "Token successfully sent to your email. Please check.";
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User registeredUser = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoginResponse loginResponse = new LoginResponse();
//        if (registeredUser.getPassword().equals(loginRequest.getPassword())) {
        if(BCrypt.checkpw(loginRequest.getPassword(), registeredUser.getPassword())) {
            loginResponse.setMessage("successful login");
            return loginResponse;
        }
        else
            loginResponse.setMessage("Unsuccessful login, Kindly try again");
        return loginResponse;
    }

    @Override
    public Response deleteUser(int id, DeleteRequest deleteRequest) {
        User foundUser = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User doesn't exist"));
        String randomToken = UUID.randomUUID().toString();
        String encoded = BCrypt.hashpw(randomToken, BCrypt.gensalt());
        if (BCrypt.checkpw(deleteRequest.getPassword(), foundUser.getPassword())) {
            String deleteCustomer = "Deleted" + " " + foundUser.getEmail() + " " + encoded;
            foundUser.setEmail(deleteCustomer);
            userRepository.save(foundUser);
            return new Response("User deleted");
        } else {
            throw new RuntimeException("User can't be deleted");
        }
    }

//    @Override
//    public List<User> getAllUserFromDb() {
//        return userRepository.findAll();
//    }

    @Override
    public Response updateUser(int id, UpdateRequest updateRequest) {
        var user = userRepository.findById(id);
        if(user.isEmpty()) throw new RuntimeException("User not found");
        UpdateRegisteredUser(updateRequest, user);
        return new Response("user details update successfully");
    }

    @Override
    public Response deleteAllUsers() {
        userRepository.deleteAll();
        return new Response("You have deleted all users");
    }

    private void UpdateRegisteredUser(UpdateRequest updateRequest, Optional<User> user) {
        User foundUser = user.get();
        foundUser.setFirstName(updateRequest.getFirstName() != null && !updateRequest.getFirstName().equals("")
                ? updateRequest.getFirstName() : foundUser.getFirstName());
        foundUser.setLastName(updateRequest.getLastName() != null && !updateRequest.getLastName().equals("")
                ? updateRequest.getLastName() : foundUser.getLastName());
        if(userRepository.findByPhoneNumber(updateRequest.getPhone()).isPresent())
            throw new RuntimeException("This Phone Number has been taken, kindly use another");
        else
            foundUser.setPhoneNumber(updateRequest.getPhone() != null && !updateRequest.getPhone()
                .equals("") ? updateRequest.getPhone() : foundUser.getPhoneNumber());
        userRepository.save(foundUser);
    }
}
