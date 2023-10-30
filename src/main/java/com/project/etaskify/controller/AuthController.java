package com.project.etaskify.controller;

import com.project.etaskify.model.dto.request.LoginRequest;
import com.project.etaskify.model.dto.request.OrganizationRegisterRequest;
import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.model.dto.request.VerifyOtpRequest;
import com.project.etaskify.model.dto.response.AuthenticationResponse;
import com.project.etaskify.service.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("register/admin")
    public void registerAdmin(@Valid @RequestBody RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        authService.register(request);
    }

    @PutMapping("verify/otp")
    public void verifyOtpCode(@Valid @RequestBody VerifyOtpRequest request) {
        authService.verifyOtp(request);
    }

    @PostMapping("register/organization")
    public void registerOrganization(@Valid @RequestBody OrganizationRegisterRequest request) {
        authService.registerOrganization(request);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
