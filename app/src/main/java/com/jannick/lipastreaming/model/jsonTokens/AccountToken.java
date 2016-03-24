package com.jannick.lipastreaming.model.jsonTokens;

/**
 * Created by Jannick on 24-3-2016.
 */
public class AccountToken {

    private boolean succes;
    private String device;
    private String username;
    private String email;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
