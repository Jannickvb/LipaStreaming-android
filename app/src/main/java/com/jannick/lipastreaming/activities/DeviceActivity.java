package com.jannick.lipastreaming.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.adapters.DeviceAdapter;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.jannick.lipastreaming.utils.LinkUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class DeviceActivity extends AppCompatActivity {

    private ListView devices;
    private DeviceAdapter deviceAdapter;
    private String url;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        activity = this;

        refeshLayout();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setRepeatMode(Animation.RESTART);
                rotateAnimation.setDuration(1000);
                view.startAnimation(rotateAnimation);
                refeshLayout();
            }
        });

        Toolbar mToolBar;
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(mToolBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refeshLayout(){
        ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);

        url = "http://lipa.kvewijk.nl/android/device?session=" + serverRequestHandler.getLocalPreferences().getString("session","");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this,url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                Gson gson = new Gson();
                DevicesToken devicesToken = gson.fromJson(responsestr,DevicesToken.class);
                DevicesToken.Device[] list = devicesToken.getDevices();

                devices = (ListView)findViewById(R.id.list_devices);

                deviceAdapter = new DeviceAdapter(getBaseContext(), list,activity);
                devices.setAdapter(deviceAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("parse","failed device JSON parse");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        refeshLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
