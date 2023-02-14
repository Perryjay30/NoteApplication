package com.perry.ClassicNotes.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    private Diary diary;
}
