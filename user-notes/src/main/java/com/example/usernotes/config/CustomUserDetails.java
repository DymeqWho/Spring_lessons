package com.example.usernotes.config;

import com.example.usernotes.model.dao.UserEntity;
import com.example.usernotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Primary
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .authorities(userEntity.getAuthorities().stream().map(entity -> entity.getAuthority()).toArray(String[]::new))
                .build();
    }
}
