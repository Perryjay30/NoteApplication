package com.perry.ClassicNotes.Data.dto.Request;

import lombok.Data;

@Data
public class UpdateRequest {
    private int id;
    private String phone;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
