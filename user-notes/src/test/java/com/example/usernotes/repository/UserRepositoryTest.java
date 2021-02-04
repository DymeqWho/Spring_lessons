package com.example.usernotes.repository;

import com.example.usernotes.model.dao.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(statements = {
            "insert into users(id, username, password, active) " +
                    "values(999, 'user', 'password', 1)"
    })
    void shouldReturnUserWhenExists(){
        UserEntity user = userRepository.findByUsername("user").orElse(null);
        assertEquals("user", user.getUsername());
        assertEquals(999L, user.getId());
    }



}