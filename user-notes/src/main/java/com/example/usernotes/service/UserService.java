package com.example.usernotes.service;


import com.example.usernotes.model.dao.AuthorityEntity;
import com.example.usernotes.model.dao.UserEntity;
import com.example.usernotes.model.dto.UserInfoResponse;
import com.example.usernotes.model.dto.UserRegisterRequest;
import com.example.usernotes.model.exceptions.AppErrorMessage;
import com.example.usernotes.model.exceptions.AppException;
import com.example.usernotes.repository.AuthorityRepository;
import com.example.usernotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserInfoResponse getUserByUsername(String username){
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(AppErrorMessage.MISSING_USER, username));
        return userEntityToUserInfoResponse(userEntity);
    }

    public UserInfoResponse saveUser(UserRegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(registerRequest.getPassword());
        userEntity.setActive(registerRequest.isActive());
        List<AuthorityEntity> authorities = registerRequest.getRoles().stream()
                .map(str -> authorityRepository.findByAuthority("ROLE_"+ str).orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toList());
        userEntity.setAuthorities(authorities);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userEntityToUserInfoResponse(savedEntity);
    }

    private UserInfoResponse userEntityToUserInfoResponse(UserEntity userEntity) {
        return UserInfoResponse.builder()
                .username(userEntity.getUsername())
                .build();
    }
}
