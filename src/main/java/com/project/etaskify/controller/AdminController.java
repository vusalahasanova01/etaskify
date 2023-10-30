package com.project.etaskify.controller;

import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.service.AdminService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class AdminController {

    private final AdminService adminService;

    @PostMapping("create-user")
    public void createUser(@Valid @RequestBody RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        adminService.createUser(request);
    }


}
