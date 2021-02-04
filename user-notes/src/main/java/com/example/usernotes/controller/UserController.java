package com.example.usernotes.controller;

import com.example.usernotes.model.dto.UserInfoResponse;
import com.example.usernotes.model.dto.UserRegisterRequest;
import com.example.usernotes.model.exceptions.AppErrorMessage;
import com.example.usernotes.model.exceptions.AppException;
import com.example.usernotes.model.exceptions.ErrorResponse;
import com.example.usernotes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

//    @GetMapping(path = "/api/users/{username}")
//    public UserInfoResponse getUserByUsername(@PathVariable(name = "username") String username){
//        return userService.getUserByUsername(username);
//    }

    @GetMapping(path = "/api/users/{username}")
    public ResponseEntity<UserInfoResponse> getUserByUsername(@PathVariable(name = "username") String username,
                                                              Principal principal){
        if(principal.getName().equals(username) == false){
            throw new AppException(AppErrorMessage.NOT_ALLOWED);
        }
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping(path = "/api/users")
    public UserInfoResponse createUser(@Valid @RequestBody UserRegisterRequest registerRequest){
        return userService.saveUser(registerRequest);
    }

    @ExceptionHandler({AppException.class})
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException){
        return ResponseEntity
                .status(appException.getResponseStatus())
                .body(new ErrorResponse(appException.getMessage()));
    }


}
