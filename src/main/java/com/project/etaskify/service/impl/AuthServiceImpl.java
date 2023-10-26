package com.project.etaskify.service.impl;

import com.project.etaskify.error.exception.DuplicateUsernameException;
import com.project.etaskify.error.exception.PasswordsNotMatchedException;
import com.project.etaskify.model.dto.request.LoginRequest;
import com.project.etaskify.model.dto.request.UserRegisterRequest;
import com.project.etaskify.model.dto.response.AuthenticationResponse;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.mapper.UserMapper;
import com.project.etaskify.security.JwtProvider;
import com.project.etaskify.service.AuthService;
import com.project.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthenticationResponse> register(UserRegisterRequest request) {
//        if (userService.getByUsername(request.getEmail()).isPresent()) {
//            throw new DuplicateUsernameException("duplicate username");
//        }

        if (!request.isPasswordsMatched()) {
            throw new PasswordsNotMatchedException("passwords not matched");
        }

        User user = userMapper.toUser(request);

        userService.save(user);

        String jwtToken = jwtProvider.generateToken(user);

        return ResponseEntity.ok(AuthenticationResponse.of(jwtToken));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        User user = userService.getByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("user not found."));
        String jwtToken = jwtProvider.generateToken(user);

        return ResponseEntity.ok(AuthenticationResponse.of(jwtToken));
    }

}