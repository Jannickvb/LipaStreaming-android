package com.jannick.lipastreaming.activities.edit;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jannick.lipastreaming.R;

public class DeviceEditActivity extends AppCompatActivity {

    private String id,description,name,location;

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

        TextView textViewId = (TextView)findViewById(R.id.tv_id);
        TextView textViewName = (TextView)findViewById(R.id.tv_name);
        TextView textViewDesc = (TextView)findViewById(R.id.tv_desc);
        TextView textViewLocation = (TextView)findViewById(R.id.tv_location);

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
        Snackbar.make(view, "Not yet implemented", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
