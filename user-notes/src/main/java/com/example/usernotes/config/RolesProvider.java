package com.example.usernotes.config;


import com.example.usernotes.model.dao.AuthorityEntity;
import com.example.usernotes.model.dao.UserEntity;
import com.example.usernotes.repository.AuthorityRepository;
import com.example.usernotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RolesProvider implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        AuthorityEntity adminRole = new AuthorityEntity();
        adminRole.setAuthority("ROLE_ADMIN");
        adminRole = authorityRepository.save(adminRole);

        AuthorityEntity userRole = new AuthorityEntity();
        userRole.setAuthority("ROLE_USER");
        userRole = authorityRepository.save(userRole);

        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setAuthorities(Arrays.asList(adminRole, userRole));
        userRepository.save(admin);
    }
}
