package com.example.usernotes.controller;

import com.example.usernotes.model.dto.CreateNoteRequest;
import com.example.usernotes.model.dto.Note;
import com.example.usernotes.model.dto.NotesListResponse;
import com.example.usernotes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class NotesController {

    private final NoteService noteService;

    @GetMapping("/users/{username}/notes")
    public NotesListResponse getNotesForUser(@PathVariable(name = "username") String username){
        return noteService.getNotesForUser(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{username}/notes")
    public void createNoteForUser(@PathVariable(name = "username") String username, @RequestBody CreateNoteRequest request){
        noteService.createNoteForUser(username, request);
    }

    @GetMapping("/notes/{noteId}")
    public Note getNoteById(@PathVariable("noteId") Long id){
        throw new RuntimeException();
    }

}
