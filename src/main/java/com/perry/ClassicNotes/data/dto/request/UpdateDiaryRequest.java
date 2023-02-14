package com.perry.ClassicNotes.data.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateDiaryRequest {
    private int diaryId;
    @NotBlank(message = "This field must not be empty")
    private String diaryName;
    @NotBlank(message = "This field must not be empty")
    private String diaryEntries;
}
