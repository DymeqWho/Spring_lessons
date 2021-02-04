package com.example.usernotes.model.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ErrorResponse {

    private String message;
    private String errorTime;

    public ErrorResponse(String message) {
        this.message = message;
        this.errorTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
