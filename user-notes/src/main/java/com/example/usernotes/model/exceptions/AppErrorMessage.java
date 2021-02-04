package com.example.usernotes.model.exceptions;

import lombok.Getter;

@Getter
public enum AppErrorMessage {

    MISSING_USER("User with username %s not found", 512),
    NOT_ALLOWED("You cannot get data", 403);

    private String messageTemplate;
    private int status;

    AppErrorMessage(String messageTemplate, int status) {
        this.messageTemplate = messageTemplate;
        this.status = status;
    }



}
