package com.project.etaskify.service.impl;

import com.project.etaskify.error.exception.DuplicateFieldException;
import com.project.etaskify.model.dto.request.OrganizationRegisterRequest;
import com.project.etaskify.model.entity.Organization;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.mapper.OrganizationMapper;
import com.project.etaskify.repository.OrganizationRepository;
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

    private final OrganizationRepository organizationRepository;

    @Override
    public Optional<Organization> getOrganizationByName(String name) {
        return organizationRepository.findOrganizationByName(name);
    }

    @Override
    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }

    @Override
    @Transactional
    public void save(Organization organization) {
        organizationRepository.insert(organization);
    }

}
