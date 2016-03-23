package com.jannick.lipastreaming.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.adapters.DeviceAdapter;
import com.jannick.lipastreaming.adapters.StreamAdapter;
import com.jannick.lipastreaming.model.DeviceItem;
import com.jannick.lipastreaming.model.StreamItem;

import java.util.List;

public class DeviceActivity extends AppCompatActivity {

    private ListView devices;
    private DeviceAdapter deviceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        DeviceItem[] temp = new DeviceItem[10];
        for(int i = 0;i<10;i++){
            temp[i] = new DeviceItem("BESTE ID OOIT","device " + (i + 1),"een mooi dingeske","Avans");
        }

        devices = (ListView)findViewById(R.id.list_devices);
        deviceAdapter = new DeviceAdapter(this, temp);
        devices.setAdapter(deviceAdapter);

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
