package com.example.usernotes.model.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class AppException extends RuntimeException {

    private int responseStatus;
    //String detailedMessage, niejawny wynika z dziedziczenia

    public AppException(AppErrorMessage message, String ... params){
        super(String.format(message.getMessageTemplate(), params));
        this.responseStatus = message.getStatus();
    }

}
