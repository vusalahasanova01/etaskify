package com.project.etaskify.config;

import com.project.etaskify.model.mapper.OrganizationMapper;
import com.project.etaskify.model.mapper.TaskMapper;
import com.project.etaskify.model.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

    @Bean
    public OrganizationMapper organizationMapper() {
        return OrganizationMapper.INSTANCE;
    }

    @Bean
    public TaskMapper taskMapper() {
        return TaskMapper.INSTANCE;
    }

}