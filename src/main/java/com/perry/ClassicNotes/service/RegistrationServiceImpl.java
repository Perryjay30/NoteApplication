package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.dto.request.SendOtpRequest;
import com.perry.ClassicNotes.data.dto.request.UserRegistrationRequest;
import com.perry.ClassicNotes.data.models.User;
import com.perry.ClassicNotes.data.repository.UserRepository;
import com.perry.ClassicNotes.utils.validators.UserDetailsValidators;
import org.springframework.stereotype.Service;

import static com.perry.ClassicNotes.data.models.Status.UNVERIFIED;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;

    private final UserRepository userRepository;

    public RegistrationServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public String register(UserRegistrationRequest registrationRequest) {
        if(!UserDetailsValidators.isValidEmailAddress(registrationRequest.getEmail()))
            throw new IllegalArgumentException(String.format("Email address %s is invalid", registrationRequest.getEmail()));

//        if(UserDetailsValidators.isValidPhoneNumber(registrationRequest.getPhoneNumber()))
//            throw new LogisticsException("Please, Enter a valid Phone Number");

        User user = registeringCustomer(registrationRequest);
        userRepository.save(user);
        SendOtpRequest sendOtpRequest = new SendOtpRequest();
        sendOtpRequest.setEmail(registrationRequest.getEmail());
        return userService.sendOTP(sendOtpRequest);
    }

    private User registeringCustomer(UserRegistrationRequest customerRegistrationRequest) {
        User user = new User();
        user.setFirstName(customerRegistrationRequest.getFirstName());
        user.setLastName(customerRegistrationRequest.getLastName());
        if(userRepository.findUserByEmail(customerRegistrationRequest.getEmail()).isPresent())
            throw new RuntimeException("This email has been taken, kindly register with another email address");
        else
            user.setEmail(customerRegistrationRequest.getEmail());
        user.setPassword(customerRegistrationRequest.getPassword());
        user.setStatus(UNVERIFIED);
        return user;
    }
}
