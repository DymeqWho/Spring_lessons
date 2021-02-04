package com.example.usernotes.controller;

import com.example.usernotes.model.exceptions.AppException;
import com.example.usernotes.model.exceptions.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({AppException.class})
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException) {
        return ResponseEntity
                .status(appException.getResponseStatus())
                .body(new ErrorResponse(appException.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException validationException) {
        return ResponseEntity
                .status(400)
                .body(validationException.getAllErrors().stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.toList())
                );
    }
}
