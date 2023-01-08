package com.perry.ClassicNotes.data.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String body;
    private String title;
    private String date;
    private int diaryId;
}
