package com.project.etaskify.config;

import com.project.etaskify.model.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public UserMapper userModelMapper() {
        return UserMapper.INSTANCE;
    }

}