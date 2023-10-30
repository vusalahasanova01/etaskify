package com.project.etaskify.service;


import com.project.etaskify.model.entity.Organization;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrganizationService {

    Optional<Organization> getOrganizationByName(String name);

    Optional<Organization> getOrganizationById(Long id);

    @Transactional
    void save(Organization organization);

}
