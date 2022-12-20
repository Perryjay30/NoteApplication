package com.perry.ClassicNotes.Data.dto.Request;


import lombok.Data;

@Data
public class UpdateDiaryRequest {
    private int diaryId;
    private String diaryName;
    private String diaryEntries;
}
