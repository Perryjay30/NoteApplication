package com.perry.ClassicNotes.Data.dto.Response;

import lombok.Data;

@Data

public class WriteResponse {
    private int id;
    private int statusCode;
    private String message;
}
