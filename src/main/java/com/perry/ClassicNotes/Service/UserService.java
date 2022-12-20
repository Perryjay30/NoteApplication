package com.perry.ClassicNotes.Service;


import com.perry.ClassicNotes.Data.Models.User;
import com.perry.ClassicNotes.Data.dto.Request.LoginRequest;
import com.perry.ClassicNotes.Data.dto.Request.UpdateRequest;
import com.perry.ClassicNotes.Data.dto.Request.UserRegistrationRequest;
import com.perry.ClassicNotes.Data.dto.Response.LoginResponse;
import com.perry.ClassicNotes.Data.dto.Response.Response;
import com.perry.ClassicNotes.Data.dto.Response.UserRegistrationResponse;

import java.util.List;

public interface UserService {
   UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest);
   LoginResponse login(LoginRequest loginRequest);
   Response deleteUser(int id);
   List<User> getAllUserFromDb();
   Response updateUser(UpdateRequest updateRequest);
}
