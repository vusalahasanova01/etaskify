package com.project.etaskify.model.dto.request;

import com.project.etaskify.error.constraints.Email;
import com.project.etaskify.error.constraints.ValidPassword;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @Size(min = 1, max = 255)
    private String username;

    @ValidPassword
    private String password;

}
