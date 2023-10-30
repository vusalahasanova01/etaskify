package com.project.etaskify.service.impl;

import com.project.etaskify.error.exception.DuplicateFieldException;
import com.project.etaskify.error.exception.PasswordsNotMatchedException;
import com.project.etaskify.error.exception.UserNotFoundException;
import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.model.entity.Organization;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.mapper.UserMapper;
import com.project.etaskify.service.AdminService;
import com.project.etaskify.service.OrganizationService;
import com.project.etaskify.service.UserService;
import com.project.etaskify.util.EmailProvider;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final OrganizationService organizationService;
    private final UserMapper userMapper;
    private final EmailProvider emailProvider;

    @Override
    public void createUser(RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        if (!request.isPasswordsMatched()) {
            throw new PasswordsNotMatchedException("passwords not matched");
        }

        if (userService.getUserByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateFieldException("duplicate username");
        }

        if (userService.getUserByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateFieldException("duplicate email");
        }

        // get admin user from db
        String adminUsername = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User admin = userService.getUserByUsername(adminUsername).stream().findFirst().orElseThrow(() -> new UserNotFoundException("user not found"));

        // setting up user
        User user = userMapper.toUser(request);
        user.setOrganizationId(admin.getOrganizationId());

        userService.saveUser(user);

        Organization organization = organizationService.getOrganizationById(admin.getOrganizationId()).stream().findFirst().orElseThrow(() -> new UserNotFoundException("organization not found"));

        emailProvider.sendWelcomeToUser(user, adminUsername, organization.getName());

    }
}
