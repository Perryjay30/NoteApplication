package com.perry.ClassicNotes.data.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "This field must not be empty")
    private String email;
    @NotBlank(message = "This field must not be empty")
    private String password;
}
