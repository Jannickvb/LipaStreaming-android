package com.jannick.lipastreaming.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.activities.launch.LoginActivity;
import com.jannick.lipastreaming.adapters.DeviceAdapter;
import com.jannick.lipastreaming.adapters.GridAdapter;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.AccountToken;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.jannick.lipastreaming.utils.LayoutUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private AccountToken accountToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refreshInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            LayoutUtils.navigateToActivity(this,SettingsActivity.class);
        } else if(id == R.id.action_logout){
            ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);
            if(serverRequestHandler.logOut(this));{
                serverRequestHandler.clearLoginData();
                LayoutUtils.navigateToActivity(this, LoginActivity.class);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void refreshInfo(){
        ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);

        String url = "http://lipa.kvewijk.nl/android/account.php?session=" + serverRequestHandler.getLocalPreferences().getString("session","");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                Gson gson = new Gson();
                AccountToken accountToken = gson.fromJson(responsestr, AccountToken.class);
                setDisplayInfo(accountToken);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("parse", "failed device JSON parse");
            }
        });
    }

    private void setDisplayInfo(AccountToken accountToken){
        this.accountToken = accountToken;
        TextView device = (TextView)findViewById(R.id.tv_account_device_id);
        device.setText(accountToken.getDevice());

        TextView username = (TextView)findViewById(R.id.tv_account_username);
        username.setText(accountToken.getUsername());

        TextView email = (TextView)findViewById(R.id.tv_account_email);
        email.setText(accountToken.getEmail());

        GridView gridView = (GridView) findViewById(R.id.main_grid_container);
        gridView.setAdapter(new GridAdapter(this, accountToken));
    }
}
