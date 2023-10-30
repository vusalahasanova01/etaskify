package com.project.etaskify.service.impl;

import com.project.etaskify.error.exception.OrganizationIsNotMatched;
import com.project.etaskify.error.exception.TaskNotFoundException;
import com.project.etaskify.error.exception.UserNotFoundException;
import com.project.etaskify.model.dto.TaskStatus;
import com.project.etaskify.model.dto.request.CreateTaskRequest;
import com.project.etaskify.model.dto.request.UpdateTaskRequest;
import com.project.etaskify.model.dto.response.TaskResponse;
import com.project.etaskify.model.dto.response.UserResponse;
import com.project.etaskify.model.entity.Task;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.mapper.TaskMapper;
import com.project.etaskify.repository.TaskRepository;
import com.project.etaskify.service.TaskService;
import com.project.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final UserService userService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> getAllTasks(Long userId) {
        List<Task> tasks = taskRepository.findAllTasks(userId);
        return taskMapper.toTaskResponses(tasks);
    }

    @Override
    public List<TaskResponse> getUserTasks(Long userId) {
        List<Task> tasks = taskRepository.findUserTasks(userId);
        return taskMapper.toTaskResponses(tasks);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findTaskById(id).orElse(null);
        return taskMapper.toTaskResponse(task);
    }

    @Transactional
    @Override
    public void insert(CreateTaskRequest request) {
        User fromUser = getUserFromSecurityContext();

        UserResponse toUser = getToUserByIdOrElseThrow(request.getToId());

        isOrganizationsMatchedOrElseThrow(fromUser, toUser);

        Task task = taskMapper.toTask(request);
        task.setFromId(fromUser.getId());

        taskRepository.insert(task);
    }

    @Transactional
    @Override
    public void update(UpdateTaskRequest request) {
        getTaskByIdOrElseThrow(request.getId());

        User fromUser = getUserFromSecurityContext();

        UserResponse toUser = getToUserByIdOrElseThrow(request.getToId());

        isOrganizationsMatchedOrElseThrow(fromUser, toUser);

        Task task = taskMapper.toTask(request);

        taskRepository.update(task);
    }

    @Transactional
    @Override
    public void updateStatus(TaskStatus status, Long id) {
        TaskResponse taskById = getTaskByIdOrElseThrow(id);

        User fromUser = getUserFromSecurityContext();

        if (Objects.isNull(taskById.getFromId()) || !taskById.getFromId().equals(fromUser.getId())) {
            throw new TaskNotFoundException("task is not in this organization");
        }

        taskRepository.updateStatus(status.getId(), id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        TaskResponse taskById = getTaskByIdOrElseThrow(id);

        User fromUser = getUserFromSecurityContext();

        if (Objects.isNull(taskById.getFromId()) || !taskById.getFromId().equals(fromUser.getId())) {
            throw new TaskNotFoundException("task is not in this organization");
        }

        taskRepository.delete(id);
    }

    private TaskResponse getTaskByIdOrElseThrow(Long id) {
        TaskResponse taskById = getTaskById(id);

        if (Objects.isNull(taskById)) {
            throw new TaskNotFoundException("task not found");
        }
        return taskById;
    }

    private UserResponse getToUserByIdOrElseThrow(Long id) {
        UserResponse toUser = userService.getUserById(id);

        if (Objects.isNull(toUser)) {
            throw new UserNotFoundException("to user not found");
        }
        return toUser;
    }

    private void isOrganizationsMatchedOrElseThrow(User fromUser, UserResponse toUser) {
        if (Objects.isNull(fromUser.getOrganizationId()) ||
                !fromUser.getOrganizationId().equals(toUser.getOrganizationId())) {
            throw new OrganizationIsNotMatched("organizations is not same");
        }
    }

    private User getUserFromSecurityContext() {
        String fromUsername = getUsernameFromSecurityContext();
        return userService.getUserByUsername(fromUsername).orElseThrow(() -> new UserNotFoundException("from user not found"));
    }

    private String getUsernameFromSecurityContext() {
        return ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
