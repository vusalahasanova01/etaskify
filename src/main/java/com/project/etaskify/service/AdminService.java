package com.project.etaskify.service;


import com.project.etaskify.model.dto.request.RegisterRequest;
import jakarta.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

public interface AdminService {

    @Transactional
    void createUser(RegisterRequest registerRequest) throws MessagingException, UnsupportedEncodingException;

}
