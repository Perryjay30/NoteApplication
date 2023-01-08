package com.perry.ClassicNotes.data.dto.response;

import lombok.Data;

@Data

public class WriteResponse {
    private int id;
    private int statusCode;
    private String message;
}
