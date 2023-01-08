package com.perry.ClassicNotes.data.repository;


import com.perry.ClassicNotes.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
//    boolean findByEmail(String email);
}
