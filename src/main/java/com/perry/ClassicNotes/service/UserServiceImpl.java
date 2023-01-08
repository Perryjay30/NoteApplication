package com.perry.ClassicNotes.service;

;
import com.perry.ClassicNotes.data.models.User;
import com.perry.ClassicNotes.data.repository.UserRepository;
import com.perry.ClassicNotes.data.dto.request.LoginRequest;
import com.perry.ClassicNotes.data.dto.request.UpdateRequest;
import com.perry.ClassicNotes.data.dto.request.UserRegistrationRequest;
import com.perry.ClassicNotes.data.dto.response.LoginResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.UserRegistrationResponse;
import com.perry.ClassicNotes.validators.UserDetailsValidators;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) {
//        if(!UserDetailsValidators.isValidPassword(userRegistrationRequest.getPassword()))
//            throw new RuntimeException(String.format("password %s is invalid", userRegistrationRequest.getPassword()));

        if(!UserDetailsValidators.isValidEmailAddress(userRegistrationRequest.getEmail()))
            throw new RuntimeException(String.format("Email address %s is invalid", userRegistrationRequest.getEmail()));

        if(!UserDetailsValidators.isValidPhoneNumber(userRegistrationRequest.getPhoneNumber()))
            throw new RuntimeException("Please, Enter a valid Phone Number");

        User user = newUser(userRegistrationRequest);
        User savedUser = userRepository.save(user);

        return newUserRegistrationResponse(savedUser);

    }

    private UserRegistrationResponse newUserRegistrationResponse(User savedUser) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setMessage("Registration successful");
        response.setStatusCode(201);
        response.setId(savedUser.getId());
        return response;
    }

    private User newUser(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
       if(userRepository.findUserByEmail(userRegistrationRequest.getEmail()).isPresent())
            throw new RuntimeException("This email has been taken, kindly register with another email address");
        else
            user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(userRegistrationRequest.getPassword());
        if(userRepository.findByPhoneNumber(userRegistrationRequest.getPhoneNumber()).isPresent())
            throw new RuntimeException("This Phone Number has been taken, kindly use another");
        else
            user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        return user;
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
    public Response deleteUser(int id) {
        userRepository.deleteById(id);
        return new Response("User deleted");
    }

    @Override
    public List<User> getAllUserFromDb() {
        return userRepository.findAll();
    }

    @Override
    public Response updateUser(UpdateRequest updateRequest) {
        var user = userRepository.findById(updateRequest.getId());
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
        foundUser.setPhoneNumber(updateRequest.getPhone() != null && !updateRequest.getPhone()
                .equals("") ? updateRequest.getPhone() : foundUser.getPhoneNumber());
        foundUser.setPassword(updateRequest.getPassword());
        userRepository.save(foundUser);
    }
}
