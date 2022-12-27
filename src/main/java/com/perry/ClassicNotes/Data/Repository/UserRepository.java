package com.perry.ClassicNotes.Data.Repository;


import com.perry.ClassicNotes.Data.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
//    boolean findByEmail(String email);
}
