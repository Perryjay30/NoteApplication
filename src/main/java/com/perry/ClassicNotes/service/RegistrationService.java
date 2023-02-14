package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.dto.request.UserRegistrationRequest;

public interface RegistrationService {
    String register(UserRegistrationRequest registrationRequest);
}
