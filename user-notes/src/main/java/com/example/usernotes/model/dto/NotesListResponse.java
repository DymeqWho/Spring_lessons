package com.example.usernotes.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NotesListResponse {
    private List<Note> noteList;
}
