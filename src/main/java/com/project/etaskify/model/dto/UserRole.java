package com.project.etaskify.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserRole {

    ADMIN(1),
    USER(2),
    UNKNOWN(0);

    private Integer id;

    public static UserRole of(Integer userRole) {
        return Arrays.stream(UserRole.values())
                .filter(u -> u.getId().equals(userRole))
                .findFirst()
                .orElse(UNKNOWN);
    }

}
