package com.jannick.lipastreaming.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.adapters.DeviceAdapter;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class DeviceActivity extends AppCompatActivity {

    private ListView devices;
    private DeviceAdapter deviceAdapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        url = "http://lipa.kvewijk.nl/android/devices.php?session=" + ServerRequestHandler.sessionID;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this,url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                Gson gson = new Gson();
                DevicesToken devicesToken = gson.fromJson(responsestr,DevicesToken.class);
                DevicesToken.Device[] list = devicesToken.getDevices();

                devices = (ListView)findViewById(R.id.list_devices);

                deviceAdapter = new DeviceAdapter(getBaseContext(), list);
                devices.setAdapter(deviceAdapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("parse","failed device JSON parse");
            }
        });

//        DevicesToken.Device[] temp = new DevicesToken.Device[10];
//        for(int i = 0;i<10;i++){
//            temp[i] = new DevicesToken.Device();
//            temp[i].setLocation("Test");
//            temp[i].setName("Device " + (i + 1));
//            temp[i].setDescription("test dingeske");
//            temp[i].setDevice("IDKE");
//        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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


}
