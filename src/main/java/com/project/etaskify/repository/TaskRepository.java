package com.project.etaskify.repository;


import com.project.etaskify.model.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    List<Task> findAllTasks(@Param("userId") Long userId);
    List<Task> findUserTasks(@Param("userId") Long userId);
    Optional<Task> findTaskById(@Param("id") Long id);

    void insert(Task task);
    void update(Task task);
    void updateStatus(@Param("statusId") Integer statusId,
                      @Param("id") Long id);

    void delete(@Param("id") Long id);

}
