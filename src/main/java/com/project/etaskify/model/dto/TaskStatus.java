package com.project.etaskify.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TaskStatus {

    OPEN(1),
    IN_PROGRESS(2),
    REJECTED(3),
    DONE(4),
    UNKNOWN(0);

    private Integer id;

    public static TaskStatus of(Integer status) {
        return Arrays.stream(TaskStatus.values())
                .filter(s -> s.getId().equals(status))
                .findFirst()
                .orElse(UNKNOWN);
    }

}
