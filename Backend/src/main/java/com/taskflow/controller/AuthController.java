package com.taskflow.controller;

import com.taskflow.dto.LoginRequest;
import com.taskflow.model.User;
import com.taskflow.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.save(user);
    }


    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }
}