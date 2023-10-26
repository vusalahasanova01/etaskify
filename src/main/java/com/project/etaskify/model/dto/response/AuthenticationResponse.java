package com.project.etaskify.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String token;

    public static AuthenticationResponse of(String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
