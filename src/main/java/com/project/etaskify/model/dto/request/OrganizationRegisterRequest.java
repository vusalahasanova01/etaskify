package com.project.etaskify.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.etaskify.error.constraints.Email;
import com.project.etaskify.error.constraints.ValidPassword;
import lombok.Data;

import java.util.Objects;

@Data
public class OrganizationRegisterRequest {

    @Email
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
