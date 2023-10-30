package com.project.etaskify.model.entity;

import com.project.etaskify.model.dto.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;
    private String title;
    private String description;
    private Long fromId;
    private Long toId;
    private LocalDateTime deadline;
    private TaskStatus status;


    public Integer getStatus() {
        return status.getId();
    }

    public TaskStatus getStatusAsEnum() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = TaskStatus.of(status);
    }

    public void setStatusAsEnum(TaskStatus status) {
        this.status = status;
    }


}
