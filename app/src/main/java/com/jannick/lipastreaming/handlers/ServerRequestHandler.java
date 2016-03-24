package com.jannick.lipastreaming.handlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.model.User;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.jannick.lipastreaming.model.jsonTokens.LoginToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.net.HttpURLConnection;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jannick on 17-3-2016.
 */
public class ServerRequestHandler {

    public static final String SP_NAME = "activeSession";
    private SharedPreferences local;

    public ServerRequestHandler(Context context){
        local = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }

    public void storeSessionData(LoginToken token){
        SharedPreferences.Editor editor = local.edit();
        editor.putString("session", token.getSession());
        editor.commit();
    }

    public LoginToken getCurrentToken(){
        LoginToken token = new LoginToken();
        token.setSession(local.getString("session", ""));
        token.setSuccess(true);
        return token;
    }

    public void clearLoginData(){
        SharedPreferences.Editor editor = local.edit();
        editor.clear();
        editor.commit();
    }

    private boolean logStatus = false;
    public boolean logOut(final Context context){
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://lipa.kvewijk.nl/android/logout.php?session="+local.getString("session","");
        client.get(context, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                logStatus = true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                logStatus = false;
            }
        });
        return logStatus;
    }

    public SharedPreferences getLocalPreferences() {
        return local;
    }
}
