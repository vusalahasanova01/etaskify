package com.project.etaskify.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.etaskify.error.constraints.Email;
import com.project.etaskify.error.constraints.ValidPassword;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Data
public class RegisterRequest {

    @Size(min = 1, max = 255)
    private String username;

    @Email
    @Size(min = 1, max = 255)
    private String email;

    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;

    @JsonIgnore
    public boolean isPasswordsMatched() {
        return Objects.nonNull(password) && password.equals(confirmPassword);
    }

}
