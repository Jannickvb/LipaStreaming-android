package com.jannick.lipastreaming.activities.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jannick.lipastreaming.R;

public class ScheduleEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);

        Toolbar mToolBar;
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_close_24dp);
        setSupportActionBar(mToolBar);


    }
}
