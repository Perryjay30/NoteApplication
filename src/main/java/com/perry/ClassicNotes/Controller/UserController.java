package com.perry.ClassicNotes.Controller;

import com.perry.ClassicNotes.Data.dto.Request.LoginRequest;
import com.perry.ClassicNotes.Data.dto.Request.UpdateRequest;
import com.perry.ClassicNotes.Data.dto.Request.UserRegistrationRequest;
import com.perry.ClassicNotes.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.register(userRegistrationRequest));
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUserFromDb());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateRequest updateRequest) {
        return ResponseEntity.ok(userService.updateUser(updateRequest));
    }
}
