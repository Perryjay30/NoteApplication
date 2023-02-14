package com.perry.ClassicNotes.data.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SendOtpRequest {
    @NotBlank(message = "This field must not be empty")
    private @Email String email;
}
