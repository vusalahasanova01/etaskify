package com.project.etaskify.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.etaskify.error.constraints.Email;
import com.project.etaskify.error.constraints.ValidPassword;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Data
public class VerifyOtpRequest {

    @Size(min = 1, max = 255)
    private String username;

    @Size(min = 4, max = 4)
    private String otpCode;

}
