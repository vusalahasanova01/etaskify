package com.project.etaskify.model.entity;

import com.project.etaskify.model.dto.UserRole;
import lombok.Data;

@Data
public class UserTemp {

    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private String otpCode;

    public Integer getUserRole() {
        return userRole.getId();
    }

    public UserRole getUserRoleAsEnum() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = UserRole.of(userRole);
    }

    public void setUserRoleAsEnum(UserRole userRole) {
        this.userRole = userRole;
    }

}