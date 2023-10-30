package com.project.etaskify.service.impl;

import com.project.etaskify.error.exception.DuplicateFieldException;
import com.project.etaskify.error.exception.PasswordsNotMatchedException;
import com.project.etaskify.error.exception.UserNotFoundException;
import com.project.etaskify.error.exception.VerificationFailedException;
import com.project.etaskify.model.dto.request.LoginRequest;
import com.project.etaskify.model.dto.request.OrganizationRegisterRequest;
import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.model.dto.request.VerifyOtpRequest;
import com.project.etaskify.model.dto.response.AuthenticationResponse;
import com.project.etaskify.model.entity.Organization;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.entity.UserTemp;
import com.project.etaskify.model.mapper.OrganizationMapper;
import com.project.etaskify.model.mapper.UserMapper;
import com.project.etaskify.security.JwtProvider;
import com.project.etaskify.service.AuthService;
import com.project.etaskify.service.OrganizationService;
import com.project.etaskify.service.UserService;
import com.project.etaskify.util.EmailProvider;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final OrganizationService organizationService;
    private final UserMapper userMapper;
    private final OrganizationMapper organizationMapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailProvider emailProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void register(RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        if (!request.isPasswordsMatched()) {
            throw new PasswordsNotMatchedException("passwords not matched");
        }

        if (userService.getUserByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateFieldException("duplicate username");
        }

        if (userService.getUserByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateFieldException("duplicate email");
        }

        UserTemp userTemp = userMapper.toUserTempAdmin(request);

        emailProvider.sendRegistrationOtp(userTemp);

        if (userService.getUserTempByUsername(userTemp.getUsername()).isPresent()) {
            userService.deleteUserTempByUsername(userTemp.getUsername());
        }

        userService.saveUserTemp(userTemp);
    }

    @Override
    @Transactional
    public void verifyOtp(VerifyOtpRequest request) {
        UserTemp userTemp = userService.getUserTempByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        if (Objects.isNull(request.getOtpCode()) || Objects.isNull(userTemp.getOtpCode()) || !request.getOtpCode().equals(userTemp.getOtpCode())) {
            throw new VerificationFailedException("otp code is not mathced");
        }

        User user = userMapper.toUser(userTemp);

        userService.saveUser(user);
        userService.deleteUserTempByUsername(request.getUsername());
    }

    @Override
    @Transactional
    public void registerOrganization(OrganizationRegisterRequest request) {
        userService.getUserByUsername(request.getUsername()).orElseThrow(() -> new UserNotFoundException("user not found"));

        if (organizationService.getOrganizationByName(request.getOrganizationName()).isPresent()) {
            throw new DuplicateFieldException("duplicate organization name");
        }

        Organization organization = organizationMapper.toOrganization(request);

        organizationService.save(organization);
        userService.updateOrganizationId(request.getUsername(), request.getOrganizationName());
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        User user = userService.getUserByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("user not found."));
        String jwtToken = jwtProvider.generateToken(user);

        return ResponseEntity.ok(AuthenticationResponse.of(jwtToken));
    }

}