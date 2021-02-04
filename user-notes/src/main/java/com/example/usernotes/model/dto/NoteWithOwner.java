package com.example.usernotes.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NoteWithOwner {//extends Note {
    private String owner;
}
