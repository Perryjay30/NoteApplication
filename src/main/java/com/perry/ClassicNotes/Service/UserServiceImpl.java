package com.perry.ClassicNotes.Service;

;
import com.perry.ClassicNotes.Data.Models.User;
import com.perry.ClassicNotes.Data.Repository.UserRepository;
import com.perry.ClassicNotes.Data.dto.Request.LoginRequest;
import com.perry.ClassicNotes.Data.dto.Request.UpdateRequest;
import com.perry.ClassicNotes.Data.dto.Request.UserRegistrationRequest;
import com.perry.ClassicNotes.Data.dto.Response.LoginResponse;
import com.perry.ClassicNotes.Data.dto.Response.Response;
import com.perry.ClassicNotes.Data.dto.Response.UserRegistrationResponse;
import com.perry.ClassicNotes.Validators.UserDetailsValidators;
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
        if(!UserDetailsValidators.isValidPassword(userRegistrationRequest.getPassword()))
            throw new RuntimeException(String.format("password %s is invalid", userRegistrationRequest.getPassword()));

        if(!UserDetailsValidators.isValidEmailAddress(userRegistrationRequest.getEmail()))
            throw new RuntimeException(String.format("Email address %s is invalid", userRegistrationRequest.getEmail()));

        if(!UserDetailsValidators.isValidPhoneNumber(userRegistrationRequest.getPhoneNumber()))
            throw new RuntimeException(String.format("Please, Enter a valid Phone Number", userRegistrationRequest.getPhoneNumber()));

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
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(userRegistrationRequest.getPassword());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        return user;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User registeredUser = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
//        User registeredUser = new User();

        LoginResponse loginResponse = new LoginResponse();
        if (registeredUser.getPassword().equals(loginRequest.getPassword())) {
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
