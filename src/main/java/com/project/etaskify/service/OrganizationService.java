package com.project.etaskify.service;


import com.project.etaskify.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrganizationService {


    @Transactional
    void save(User user);

}
