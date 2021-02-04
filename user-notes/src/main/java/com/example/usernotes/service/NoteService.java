package com.example.usernotes.service;

import com.example.usernotes.model.dao.NoteEntity;
import com.example.usernotes.model.dao.UserEntity;
import com.example.usernotes.model.dto.CreateNoteRequest;
import com.example.usernotes.model.dto.Note;
import com.example.usernotes.model.dto.NotesListResponse;
import com.example.usernotes.model.exceptions.AppErrorMessage;
import com.example.usernotes.model.exceptions.AppException;
import com.example.usernotes.repository.NotesRepository;
import com.example.usernotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final UserRepository userRepository;
    private final NotesRepository notesRepository;

    public NotesListResponse getNotesForUser(String username) {
        UserEntity userEntity = getUserByIdOrThrowException(username);
        List<Note> notes = Optional.ofNullable(userEntity.getNotes())
                .map(notesEntityList -> notesEntityList.stream()
                        .map(noteEntity -> Note.builder().content(noteEntity.getContent()).build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return NotesListResponse.builder().noteList(notes).build();
    }

    private UserEntity getUserByIdOrThrowException(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(AppErrorMessage.MISSING_USER, username));
    }

    public void createNoteForUser(String username, CreateNoteRequest request) {
        UserEntity user = getUserByIdOrThrowException(username);

        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setContent(request.getContent());
        noteEntity.setOwner(user);
        NoteEntity savedNote = notesRepository.save(noteEntity);

        if(Objects.isNull(user.getNotes())){
            user.setNotes(new ArrayList<>());
        }
        user.getNotes().add(savedNote);
    }
}
