package com.spa.jwt.models;

import java.io.Serializable;

public class SPAAuthRequest {


    private String userName;
    private String password;

    public String getUsername() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SPAAuthRequest()
    {

    }

    public SPAAuthRequest(String username, String password) {
        this.setUserName(username);
        this.setPassword(password);
    }
}