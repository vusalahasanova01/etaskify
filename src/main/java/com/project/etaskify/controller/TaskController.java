package com.project.etaskify.controller;

import com.project.etaskify.model.dto.TaskStatus;
import com.project.etaskify.model.dto.request.CreateTaskRequest;
import com.project.etaskify.model.dto.request.UpdateTaskRequest;
import com.project.etaskify.model.dto.response.TaskResponse;
import com.project.etaskify.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class TaskController {

    private final TaskService taskService;

    @GetMapping("user/all/{userId}")
    public List<TaskResponse> getAllTasks(@PathVariable Long userId) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("user/id/{userId}")
    public List<TaskResponse> getUserTasks(@PathVariable Long userId) {
        return taskService.getUserTasks(userId);
    }

    @GetMapping("user/task-id/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("admin")
    public void createTask(CreateTaskRequest request) {
        taskService.insert(request);
    }

    @PutMapping("admin")
    public void updateTask(UpdateTaskRequest request) {
        taskService.update(request);
    }

    @PutMapping("admin/{taskId}/status")
    public void updateTaskStatus(@PathVariable Long taskId,
                                 @RequestParam TaskStatus status) {
        taskService.updateStatus(status, taskId);
    }

    @DeleteMapping("admin/{taskId}")
    public void updateTaskStatus(@PathVariable Long taskId) {
        taskService.delete(taskId);
    }





}
