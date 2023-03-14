package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.dto.request.UserRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationServiceImplTest {

    @Autowired
    private RegistrationService registrationService;

    @Test
    void testThatCustomerCanRegister() {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        registrationRequest.setFirstName("Brianna");
        registrationRequest.setLastName("Stones");
        registrationRequest.setEmail("mrjesus3003@gmail.com");
        registrationRequest.setPassword("Juststones&11");
        String response =
                registrationService.register(registrationRequest);
        assertEquals("Token successfully sent to your email. Please check.", response);
    }

    @Test
    void testThatCustomerRegistrationThrowsAnException() {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        registrationRequest.setFirstName("Brianna");
        registrationRequest.setLastName("Stones");
        registrationRequest.setEmail("stonbries@yahoo.com");
        registrationRequest.setPassword("Justina");
        assertThrows(RuntimeException.class, () -> registrationService.register(registrationRequest));
    }

}