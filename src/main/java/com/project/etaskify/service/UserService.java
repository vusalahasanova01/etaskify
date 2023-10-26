package com.project.etaskify.service;


import com.project.etaskify.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserService {


    @Transactional
    void save(User user);

    @Transactional
    void update(User user);

    Optional<User> getByUsername(String username);
}
