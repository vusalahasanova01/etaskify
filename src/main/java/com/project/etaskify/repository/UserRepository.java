package com.project.etaskify.repository;


import com.project.etaskify.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserRepository {

    Optional<User> findUserByEmail(@Param("username") String username);

    void insert(User user);

    void update(User user);

}
