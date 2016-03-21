package com.jannick.lipastreaming.handlers;

import android.content.Context;
import android.content.SharedPreferences;

import com.jannick.lipastreaming.model.User;

/**
 * Created by jannick on 17-3-2016.
 */
public class UserHandler {

    public static final String SP_NAME = "userDetails";
    SharedPreferences local;

    public UserHandler(Context context){
        local = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }

    public void storeUserDate(User user){
        SharedPreferences.Editor editor = local.edit();
        editor.putString("username", user.getName());
        editor.putString("password",user.getPassword());
    }

    public User getLoggedInUser(){
        return new User(local.getString("username",""),local.getString("password",""));
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor editor = local.edit();
        editor.putBoolean("loggedIn",loggedIn);
        editor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor editor = local.edit();
        editor.clear();
        editor.commit();
    }

}
