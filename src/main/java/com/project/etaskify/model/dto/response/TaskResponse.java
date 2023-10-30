package com.project.etaskify.model.dto.response;

import com.project.etaskify.model.dto.TaskStatus;
import com.project.etaskify.model.dto.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private Long fromId;
    private Long toId;
    private LocalDateTime deadline;
    private TaskStatus status;

}
