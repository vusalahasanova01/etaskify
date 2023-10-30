package com.project.etaskify.service;


import com.project.etaskify.model.dto.TaskStatus;
import com.project.etaskify.model.dto.request.CreateTaskRequest;
import com.project.etaskify.model.dto.request.UpdateTaskRequest;
import com.project.etaskify.model.dto.response.TaskResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks(Long userId);

    List<TaskResponse> getUserTasks(Long userId);
    TaskResponse getTaskById(Long id);

    @Transactional
    void insert(CreateTaskRequest request);

    @Transactional
    void update(UpdateTaskRequest request);

    @Transactional
    void updateStatus(TaskStatus status, Long id);

    @Transactional
    void delete(Long id);

}
