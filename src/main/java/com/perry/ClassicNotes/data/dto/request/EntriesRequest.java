package com.perry.ClassicNotes.data.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class EntriesRequest {
    private int id;
    @NotBlank(message = "This field must not be empty")
    private String title;
    @NotBlank(message = "This field must not be empty")
    private String body;
    private LocalDateTime theDate;
}
