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
import com.jannick.lipastreaming.adapters.AlarmAdapter;
import com.jannick.lipastreaming.adapters.ScheduleAdapter;
import com.jannick.lipastreaming.adapters.StreamAdapter;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.AlarmToken;
import com.jannick.lipastreaming.model.jsonTokens.SchedulerToken;
import com.jannick.lipastreaming.model.jsonTokens.StreamToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class AlarmActivity extends AppCompatActivity {

    ListView alarms;
    AlarmAdapter alarmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);

        String session = serverRequestHandler.getLocalPreferences().getString("session","");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, "http://lipa.kvewijk.nl/android/alarm?session=" + session, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                Gson gson = new Gson();
                AlarmToken alarmToken = gson.fromJson(responsestr, AlarmToken.class);
                AlarmToken.Alarm[] array = alarmToken.getAlarms();

                alarms = (ListView) findViewById(R.id.list_alarms);
                alarmAdapter = new AlarmAdapter(getBaseContext(), array);
                alarms.setAdapter(alarmAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("parse", "failed device JSON parse");
            }
        });

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