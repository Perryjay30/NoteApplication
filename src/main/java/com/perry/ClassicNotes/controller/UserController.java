package com.perry.ClassicNotes.controller;

import com.perry.ClassicNotes.data.dto.request.*;
import com.perry.ClassicNotes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAccount(verifyOtpRequest));
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) throws MessagingException, MessagingException {
        return ResponseEntity.ok(userService.forgotPassword(forgotPasswordRequest));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(userService.resetPassword(resetPasswordRequest));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@Valid @RequestBody @PathVariable int id, DeleteRequest deleteRequest) {
        return ResponseEntity.ok(userService.deleteUser(id, deleteRequest));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(userService.changePassword(changePasswordRequest));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody @PathVariable int id, UpdateRequest updateRequest) {
        return ResponseEntity.ok(userService.updateUser(id, updateRequest));
    }
}
