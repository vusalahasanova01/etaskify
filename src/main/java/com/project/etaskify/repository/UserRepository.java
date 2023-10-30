package com.project.etaskify.repository;


import com.project.etaskify.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    Optional<User> findUserByUsername(@Param("username") String username);
    Optional<User> findUserById(@Param("id") Long id);

    Optional<User> findUserByEmail(@Param("email") String email);

    List<User> findUsersByOrganizationId(@Param("organizationId") Long organizationId);

    void insert(User user);

    void update(User user);
    void updateOrganizationId(@Param("username") String username,
                              @Param("organizationName") String organizationName);

}
