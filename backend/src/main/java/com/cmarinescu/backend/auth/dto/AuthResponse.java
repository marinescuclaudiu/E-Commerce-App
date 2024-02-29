package com.cmarinescu.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private Long id;
    private String email;
    private String role;
    private String token;
    private String expirationTime;
    private String message;

    public AuthResponse(){

    }

    public AuthResponse(Long id, String email, String role, String message) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.message = message;
    }
}
