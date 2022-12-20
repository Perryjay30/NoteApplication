package com.perry.ClassicNotes.Data.dto.Request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntriesRequest {
    private int id;
    private String title;
    private String body;
    private LocalDateTime theDate;
}
