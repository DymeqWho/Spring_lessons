package com.example.usernotes.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Note {
    private String content;
}
