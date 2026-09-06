package com.familytours.familytourstravels.controller;

import org.springframework.web.bind.annotation.*;

import com.familytours.familytourstravels.dto.AuthRegisterRequest;
import com.familytours.familytourstravels.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password) {

        return authService.login(email, password);
    }

    @PostMapping("/register")
    public String register(
            @RequestBody AuthRegisterRequest request) {

        return authService.register(request);
    }
}