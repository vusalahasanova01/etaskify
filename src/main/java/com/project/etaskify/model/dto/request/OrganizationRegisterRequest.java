package com.project.etaskify.model.dto.request;

import com.project.etaskify.error.constraints.Email;
import com.project.etaskify.error.constraints.ValidPassword;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
public class OrganizationRegisterRequest {

    @Size(min = 1, max = 255)
    private String username;

    @Size(min = 1, max = 255)
    private String organizationName;

    @Size(min = 1, max = 20)
    private String phoneNumber;

    @Size(min = 1, max = 255)
    private String address;

}
