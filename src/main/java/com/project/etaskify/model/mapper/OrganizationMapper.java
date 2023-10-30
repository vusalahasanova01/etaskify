package com.project.etaskify.model.mapper;

import com.project.etaskify.model.dto.request.OrganizationRegisterRequest;
import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.model.entity.Organization;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.entity.UserTemp;
import com.project.etaskify.util.Constant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(target = "name", source = "organizationName")
    Organization toOrganization(OrganizationRegisterRequest request);

}
