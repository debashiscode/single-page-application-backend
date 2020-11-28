package com.spa.jwt.models;


public class SPAAuthResponse {

    private final String token;

    public SPAAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
