package com.otters.admissionsbackend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.otters.admissionsbackend.model.Role;
import lombok.Getter;

@Getter
public class StudentAuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("name")
    private String name;

    public StudentAuthenticationResponse(String accessToken, String refreshToken, String message, Role role, String name) {
        this.accessToken = accessToken;
        this.message = message;
        this.refreshToken = refreshToken;
        this.role = role;
        this.name = name;
    }

}
