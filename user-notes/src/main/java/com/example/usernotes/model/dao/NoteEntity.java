package com.example.usernotes.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "notes")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity owner;

    private String content;

}
