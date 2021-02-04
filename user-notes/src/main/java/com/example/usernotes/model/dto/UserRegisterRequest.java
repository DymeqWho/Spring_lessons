package com.example.usernotes.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotNull
    private String username;

    @NotNull
    @Pattern(regexp = "(.*[!@#$%^&*])", message = "Hasło nie spełnia wymagań bezpieczeństwa")
    @Length(min = 4, max = 10)
    private String password;

    @NotNull
    private boolean active;

    private List<String> roles;

}
