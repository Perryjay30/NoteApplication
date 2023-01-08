package com.perry.ClassicNotes.data.dto.request;


import lombok.Data;

@Data
public class UpdateDiaryRequest {
    private int diaryId;
    private String diaryName;
    private String diaryEntries;
}
