package com.perry.ClassicNotes.data.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateRequest {
    @NotBlank(message = "This field must not be empty")
    private String phone;
    @NotBlank(message = "This field must not be empty")
    private String firstName;
    @NotBlank(message = "This field must not be empty")
    private String lastName;
    @NotBlank(message = "This field must not be empty")
    private String email;

}
