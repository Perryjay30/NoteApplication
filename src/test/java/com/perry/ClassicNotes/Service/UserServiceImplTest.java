package com.perry.ClassicNotes.Service;

import com.perry.ClassicNotes.Data.Repository.UserRepository;
import com.perry.ClassicNotes.Data.dto.Request.LoginRequest;
import com.perry.ClassicNotes.Data.dto.Request.UpdateRequest;
import com.perry.ClassicNotes.Data.dto.Request.UserRegistrationRequest;
import com.perry.ClassicNotes.Data.dto.Response.LoginResponse;
import com.perry.ClassicNotes.Data.dto.Response.Response;
import com.perry.ClassicNotes.Data.dto.Response.UserRegistrationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    private UserRegistrationRequest registerFirstUserRequest;
    private UserRegistrationRequest registerSecondUserRequest;

    @BeforeEach
    void setUp() {
        registerFirstUserRequest = new UserRegistrationRequest();
        registerFirstUserRequest.setEmail("testing@gmail.com");
        registerFirstUserRequest.setPassword("Icanregister@30");
        registerFirstUserRequest.setPhoneNumber("09134568971");

        registerSecondUserRequest = new UserRegistrationRequest();
        registerSecondUserRequest.setPassword("Teslim@90");
        registerSecondUserRequest.setEmail("teslim@gmail.com");
        registerSecondUserRequest.setPhoneNumber("08146083323");
    }

    @Test
    void register() {
        UserRegistrationResponse response =
                userService.register(registerFirstUserRequest);
        UserRegistrationResponse response1 =
                userService.register(registerSecondUserRequest);
        assertNotNull(response);
        assertNotNull(response1);
        System.out.println(response);
        System.out.println(response1);
        assertEquals(201, response.getStatusCode());
        assertEquals(201, response1.getStatusCode());
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
        loginRequest.setEmail(registerFirstUserRequest.getEmail());
        loginRequest.setPassword(registerFirstUserRequest.getPassword());
        LoginResponse loginResponse = userService.login(loginRequest);
        assertEquals("successful login", loginResponse.getMessage());
    }

    @Test
    void updateUser() {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setId(9);
        updateRequest.setPassword("Iamdancing@24");
        updateRequest.setFirstName("Alphonso");
        updateRequest.setLastName("Davies");
        updateRequest.setPhone("09067047496");
        Response response = userService.updateUser(updateRequest);
        assertEquals("user details update successfully", response.getMessage());
    }

    @Test
    void deleteUser() {
        Response response = userService.deleteUser(10);
//        Response response2 = userService.deleteUser(8);
        System.out.println(response);
        assertEquals("User deleted", response.getMessage());
    }

}