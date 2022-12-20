package com.perry.ClassicNotes.Data.dto.Response;

import lombok.Data;

@Data
public class CreateResponse {
    private int id;
    private String message;
    private int statusCode;
}
