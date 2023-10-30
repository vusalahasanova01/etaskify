package com.project.etaskify.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequest {

    @Size(min = 1, max = 255)
    private String title;

    @Size(min = 1, max = 10000)
    private String description;

    @NotNull
    private Long toId;

    @NotNull
    private LocalDateTime deadline;

}
