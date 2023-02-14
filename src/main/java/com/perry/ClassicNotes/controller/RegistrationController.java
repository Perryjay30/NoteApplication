package com.perry.ClassicNotes.controller;

import com.perry.ClassicNotes.data.dto.request.UserRegistrationRequest;
import com.perry.ClassicNotes.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/api/classicNote/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
        return ResponseEntity.
                ok(registrationService.register(registrationRequest));
    }

}
