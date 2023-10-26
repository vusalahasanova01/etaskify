package com.project.etaskify.controller;

import com.project.etaskify.model.dto.request.LoginRequest;
import com.project.etaskify.model.dto.request.OrganizationRegisterRequest;
import com.project.etaskify.model.dto.request.UserRegisterRequest;
import com.project.etaskify.model.dto.response.AuthenticationResponse;
import com.project.etaskify.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
