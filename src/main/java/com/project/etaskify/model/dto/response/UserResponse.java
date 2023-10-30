package com.project.etaskify.model.dto.response;

import com.project.etaskify.model.dto.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private UserRole userRole;
    private Long organizationId;

}
