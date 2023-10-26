package com.project.etaskify.model.dto.request;

import com.project.etaskify.error.constraints.Email;
import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
