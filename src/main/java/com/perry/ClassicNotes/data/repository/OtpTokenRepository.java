package com.perry.ClassicNotes.data.repository;

import com.perry.ClassicNotes.data.models.OTPToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public interface OtpTokenRepository extends JpaRepository<OTPToken, Integer> {

    Optional<OTPToken> findByToken(String token);

    void deleteOtpTokensByExpiredAtBefore(LocalDateTime currentTime);

    @Modifying
    @Query("UPDATE OTPToken otpToken " +
            "SET otpToken.verifiedAt= ?1 " +
            "WHERE otpToken.token = ?2")
    void setVerifiedAt(LocalDateTime verified, String token);
}
