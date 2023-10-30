package com.project.etaskify.repository;


import com.project.etaskify.model.entity.UserTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserTempRepository {

    Optional<UserTemp> findUserTempByUsername(@Param("username") String username);

    void insertTempUser(UserTemp userTemp);

    void deleteTempUserByUsername(@Param("username") String username);

}
