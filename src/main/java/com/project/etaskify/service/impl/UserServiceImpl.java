package com.project.etaskify.service.impl;

import com.project.etaskify.error.exception.UserNotFoundException;
import com.project.etaskify.model.dto.response.UserResponse;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.entity.UserTemp;
import com.project.etaskify.model.mapper.UserMapper;
import com.project.etaskify.repository.UserRepository;
import com.project.etaskify.repository.UserTempRepository;
import com.project.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTempRepository userTempRepository;

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.insert(user);
    }

    @Override
    public void saveUserTemp(UserTemp userTemp) {
        userTempRepository.insertTempUser(userTemp);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    @Transactional
    public void updateOrganizationId(String username, String organizationName) {
        userRepository.updateOrganizationId(username, organizationName);
    }

    @Override
    public void deleteUserTempByUsername(String otpCode) {
        userTempRepository.deleteTempUserByUsername(otpCode);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<UserTemp> getUserTempByUsername(String username) {
        return userTempRepository.findUserTempByUsername(username);
    }


    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return UserMapper.INSTANCE.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsersByOrganizationId(Long organizationId) {
        List<User> users = userRepository.findUsersByOrganizationId(organizationId);
        return UserMapper.INSTANCE.toUserResponses(users);
    }

}
