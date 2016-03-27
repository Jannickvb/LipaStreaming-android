package com.jannick.lipastreaming.activities.edit;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.adapters.DeviceAdapter;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class DeviceEditActivity extends AppCompatActivity {

    private String id,description,name,location;
    private EditText textViewId,textViewName,textViewDesc,textViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_edit);

        Toolbar mToolBar;
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_close_24dp);
        setSupportActionBar(mToolBar);

        Bundle extras = getIntent().getExtras();

        id = extras.getString("id");
        description = extras.getString("description");
        name = extras.getString("name");
        location = extras.getString("location");

        textViewId = (EditText)findViewById(R.id.et_id);
        textViewName = (EditText)findViewById(R.id.et_name);
        textViewDesc = (EditText)findViewById(R.id.et_desc);
        textViewLocation = (EditText)findViewById(R.id.et_location);

        textViewId.setText(id);
        textViewName.setText(name);
        textViewDesc.setText(description);
        textViewLocation.setText(location);
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

        String url = "http://lipa.kvewijk.nl/android/device/edit?session="
                + serverRequestHandler.getLocalPreferences().getString("session","")
                + "&device=" + textViewId.getText().toString()
                + "&name=" + textViewName.getText().toString()
                + "&location=" + textViewLocation.getText().toString()
                + "&description=" + textViewDesc.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast("device edited");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("failed device edit");
            }
        });
        onBackPressed();
    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT);
    }
}
