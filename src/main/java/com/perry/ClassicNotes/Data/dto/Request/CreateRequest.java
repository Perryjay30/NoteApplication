package com.perry.ClassicNotes.Data.dto.Request;


import lombok.Data;

@Data
public class CreateRequest {
    private String diaryName;
    private String diaryEntries;
}
