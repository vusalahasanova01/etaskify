package com.project.etaskify.service;


import com.project.etaskify.model.dto.request.LoginRequest;
import com.project.etaskify.model.dto.request.UserRegisterRequest;
import com.project.etaskify.model.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<AuthenticationResponse> register(UserRegisterRequest request);

    ResponseEntity<AuthenticationResponse> login(LoginRequest request);

}
