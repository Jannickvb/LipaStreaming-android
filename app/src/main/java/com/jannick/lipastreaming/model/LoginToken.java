package com.jannick.lipastreaming.model;

/**
 * Created by Jannick on 23-3-2016.
 */
public class LoginToken {

    private boolean succes,error;
    private String session;

    public LoginToken(String error){
        this.error = Boolean.parseBoolean(error);
    }

    public LoginToken(String succes, String session) {
        this.succes = Boolean.parseBoolean(succes);
        this.session = session;
    }

    public boolean isSucces(){
        return succes;
    }

    public String getSession(){
        return session;
    }
}
