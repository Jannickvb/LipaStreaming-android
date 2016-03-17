package com.jannick.lipastreaming.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.adapters.StreamAdapter;
import com.jannick.lipastreaming.model.StreamItem;

public class StreamActivity extends AppCompatActivity {

    ListView streams;
    StreamAdapter streamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StreamItem[] temp = new StreamItem[10];
        for(int i = 0;i<10;i++){
            temp[i] = new StreamItem("stream " + (i + 1),"1292.22929.2");
        }

        streams = (ListView)findViewById(R.id.list_streams);
        streamAdapter = new StreamAdapter(this, temp);
        streams.setAdapter(streamAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
