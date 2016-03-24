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
import com.jannick.lipastreaming.adapters.StreamAdapter;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.jannick.lipastreaming.model.jsonTokens.StreamToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class StreamActivity extends AppCompatActivity {

    ListView streams;
    StreamAdapter streamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        String device = getIntent().getExtras().getString("device","");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, "http://lipa.kvewijk.nl/api/streams.php?id="+device, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                Gson gson = new Gson();
                StreamToken streamToken = gson.fromJson(responsestr, StreamToken.class);
                StreamToken.Stream[] array = streamToken.getStreams();

                streams = (ListView) findViewById(R.id.list_streams);
                streamAdapter = new StreamAdapter(getBaseContext(), array);
                streams.setAdapter(streamAdapter);
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
