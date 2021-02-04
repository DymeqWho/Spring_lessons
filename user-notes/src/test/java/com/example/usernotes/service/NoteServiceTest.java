package com.example.usernotes.service;

import com.example.usernotes.model.dao.NoteEntity;
import com.example.usernotes.model.dao.UserEntity;
import com.example.usernotes.model.dto.NotesListResponse;
import com.example.usernotes.repository.NotesRepository;
import com.example.usernotes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotesRepository notesRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void shouldReturnNotesForUserWhenExists(){
        NoteEntity note = new NoteEntity();
        note.setContent("custom note");
        UserEntity expectedUser = new UserEntity();
        expectedUser.setNotes(Collections.singletonList(note));

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(expectedUser));

        NotesListResponse userNotes = noteService.getNotesForUser("testUser");
        assertEquals(1, userNotes.getNoteList().size());
        assertEquals("custom note", userNotes.getNoteList().get(0).getContent());

    }


}