package com.example.usernotes.repository;

import com.example.usernotes.model.dao.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<NoteEntity, Long> {
}
