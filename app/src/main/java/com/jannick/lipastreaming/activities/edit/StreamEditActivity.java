package com.jannick.lipastreaming.activities.edit;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class StreamEditActivity extends AppCompatActivity {

    private String name,ip,path;
    private int id,port;
    private EditText etName,etIp,etPath,etPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_edit);
        Toolbar mToolBar;
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_close_24dp);
        setSupportActionBar(mToolBar);

        Bundle extras = getIntent().getExtras();

        name = extras.getString("name");
        ip = extras.getString("ip");
        path = extras.getString("path");

        id = extras.getInt("id");
        port = extras.getInt("port");

        etName = (EditText)findViewById(R.id.et_name);
        etIp = (EditText)findViewById(R.id.et_ip);
        etPath = (EditText)findViewById(R.id.et_path);
        etPort = (EditText)findViewById(R.id.et_port);

        etName.setText(name);
        etIp.setText(ip);
        etPort.setText(""+port);
        etPath.setText(path);

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

    public void saveSettings(View view){
        ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);

        String url = "http://lipa.kvewijk.nl/android/stream/edit?session="
                + serverRequestHandler.getLocalPreferences().getString("session","")
                + "&id=" + id
                + "&name=" + etName.getText().toString()
                + "&ip=" + etIp.getText().toString()
                + "&port=" + etPort.getText().toString()
                + "&path=" + etPath.getText().toString();

        Log.d("url", url);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast("stream edited");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("failed stream edit");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                onBackPressed();
            }
        });

    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

}
