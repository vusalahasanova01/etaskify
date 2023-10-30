package com.project.etaskify.service;


import com.project.etaskify.model.dto.request.LoginRequest;
import com.project.etaskify.model.dto.request.OrganizationRegisterRequest;
import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.model.dto.request.VerifyOtpRequest;
import com.project.etaskify.model.dto.response.AuthenticationResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

public interface AuthService {

    @Transactional
    void register(RegisterRequest request) throws MessagingException, UnsupportedEncodingException;

    @Transactional
    void verifyOtp(VerifyOtpRequest request);

    @Transactional
    void registerOrganization(OrganizationRegisterRequest request);

    ResponseEntity<AuthenticationResponse> login(LoginRequest request);

}
