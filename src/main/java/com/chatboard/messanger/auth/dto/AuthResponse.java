package com.chatboard.messanger.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private long expiresIn;

    public AuthResponse(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
