package com.project.etaskify.service;


import com.project.etaskify.model.dto.response.UserResponse;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.entity.UserTemp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    @Transactional
    void saveUser(User user);

    @Transactional
    void saveUserTemp(UserTemp userTemp);

    @Transactional
    void updateUser(User user);

    @Transactional
    void updateOrganizationId(String username, String organizationName);

    @Transactional
    void deleteUserTempByUsername(String username);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);
    Optional<UserTemp> getUserTempByUsername(String username);

    // for endpoint
    UserResponse getUserById(Long id);
    List<UserResponse> getUsersByOrganizationId(Long organizationId);
}
