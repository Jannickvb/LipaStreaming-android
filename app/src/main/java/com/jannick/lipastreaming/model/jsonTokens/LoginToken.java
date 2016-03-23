package com.jannick.lipastreaming.model.jsonTokens;

/**
 * Created by Jannick on 23-3-2016.
 */
public class LoginToken {

    private boolean success;
    private String session;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
