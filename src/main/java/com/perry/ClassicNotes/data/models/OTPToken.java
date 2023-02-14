package com.perry.ClassicNotes.data.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@RequiredArgsConstructor
public class OTPToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime verifiedAt;
    @ManyToOne
    private User user;

    public OTPToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
