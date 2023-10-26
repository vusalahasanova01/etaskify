package com.project.etaskify.service.impl;

import com.project.etaskify.model.entity.User;
import com.project.etaskify.repository.UserRepository;
import com.project.etaskify.service.OrganizationService;
import com.project.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void save(User user) {
        userRepository.insert(user);
    }

}
