package com.example.usernotes.front.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
public class FrontPageNewUserRequest {
    @Length(min = 5, message = "niepoprawna nazwa użytkownika")
    private String username;
}
