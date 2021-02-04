package com.example.usernotes.front;

import com.example.usernotes.front.dto.FrontPageNewUserRequest;
import com.example.usernotes.model.dto.UserRegisterRequest;
import com.example.usernotes.repository.UserRepository;
import com.example.usernotes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("newUser", new FrontPageNewUserRequest());
        return "users";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("newUser") FrontPageNewUserRequest newUserRequest) {
        userService.saveUser(UserRegisterRequest.builder()
                .username(newUserRequest.getUsername())
                .password("123")
                .roles(Collections.singletonList("USER"))
                .build());
        return "redirect:users";
    }

}
