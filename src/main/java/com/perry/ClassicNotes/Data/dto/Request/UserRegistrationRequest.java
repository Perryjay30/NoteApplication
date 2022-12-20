package com.perry.ClassicNotes.Data.dto.Request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
   private String email;
   private String password;
   private String phoneNumber;
}
