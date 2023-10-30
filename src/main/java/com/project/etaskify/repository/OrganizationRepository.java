package com.project.etaskify.repository;


import com.project.etaskify.model.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface OrganizationRepository {

    Optional<Organization> findOrganizationByName(@Param("name") String name);

    Optional<Organization> findOrganizationById(@Param("id") Long id);


    void insert(Organization organization);

}
