package com.perry.ClassicNotes.service;


import com.perry.ClassicNotes.data.models.User;
import com.perry.ClassicNotes.data.dto.request.LoginRequest;
import com.perry.ClassicNotes.data.dto.request.UpdateRequest;
import com.perry.ClassicNotes.data.dto.request.UserRegistrationRequest;
import com.perry.ClassicNotes.data.dto.response.LoginResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.UserRegistrationResponse;

import java.util.List;

public interface UserService {
   UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest);
   LoginResponse login(LoginRequest loginRequest);
   Response deleteUser(int id);
   List<User> getAllUserFromDb();
   Response updateUser(UpdateRequest updateRequest);

   Response deleteAllUsers();
}
