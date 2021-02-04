package com.example.usernotes.controller;

import com.example.usernotes.model.dto.Note;
import com.example.usernotes.model.dto.NotesListResponse;
import com.example.usernotes.model.exceptions.AppErrorMessage;
import com.example.usernotes.model.exceptions.AppException;
import com.example.usernotes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;

@WebMvcTest(controllers = NotesController.class)
class NotesControllerTest {

    @MockBean
    private NoteService noteService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testUser")
    void shouldReturnNotesForUserWhenExists() throws Exception {
        Mockito.when(noteService.getNotesForUser("testUser"))
                .thenReturn(NotesListResponse.builder()
                        .noteList(Collections.singletonList(
                                Note.builder()
                                        .content("test user note content")
                                        .build()
                        ))
                        .build());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/testUser/notes")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteList.length()",
                        equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteList[0].content",
                        equalTo("test user note content")));
    }

    @Test
    @WithMockUser(username = "testUser")
    void ShouldReturnErrorWhenUserNotExists() throws Exception {
        Mockito.when(noteService.getNotesForUser("testUser"))
                .thenThrow(new AppException(AppErrorMessage.MISSING_USER, "testUser"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/testUser/notes"))
                .andExpect(MockMvcResultMatchers.status().is(512))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", equalTo("User with username testUser not found")));
    }


}