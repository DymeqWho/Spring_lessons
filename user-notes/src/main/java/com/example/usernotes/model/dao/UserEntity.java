package com.example.usernotes.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @OneToMany
    private List<NoteEntity> notes;

    @ManyToMany
    private List<AuthorityEntity> authorities;

}
