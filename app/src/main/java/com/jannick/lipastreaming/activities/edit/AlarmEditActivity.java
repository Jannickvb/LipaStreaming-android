package com.jannick.lipastreaming.activities.edit;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.adapters.StreamAdapter;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.StreamToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class AlarmEditActivity extends AppCompatActivity {

    private String id,name,time,stream;
    private EditText editTextName,editTextTime;
    private Spinner spinnerStreams;
    private String[] streamNames;
    private StreamToken.Stream[] streams;

    private int streamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

        Toolbar mToolBar;
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_close_24dp);
        setSupportActionBar(mToolBar);

        Bundle extras = getIntent().getExtras();

        id = ""+extras.getInt("id");
        time = extras.getString("time");
        name = extras.getString("name");
        stream = extras.getString("stream");

        editTextName = (EditText)findViewById(R.id.et_name);
        editTextTime = (EditText)findViewById(R.id.et_time);
        editTextTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date d = null;
                try {
                    d = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(d);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AlarmEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextTime.setText( String.format("%02d",selectedHour) + ":" + String.format("%02d",selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        spinnerStreams = (Spinner)findViewById(R.id.sp_streams);

        editTextName.setText(name);
        editTextTime.setText(time);

        streams = getStreams();
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

    private StreamToken.Stream[] getStreams(){

        ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);

        String session = serverRequestHandler.getLocalPreferences().getString("session","");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, "http://lipa.kvewijk.nl/android/stream?session=" + session, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                Gson gson = new Gson();
                StreamToken streamToken = gson.fromJson(responsestr, StreamToken.class);
                StreamToken.Stream[] array = streamToken.getStreams();

                streams = array;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("parse", "failed device JSON parse");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                streamNames = getStreamNames(streams);
                refreshStreamItems();
            }
        });

        return streams;
    }

    private String[] getStreamNames(StreamToken.Stream[] array){
        if(array!=null){
            streamNames = new String[array.length];
            for(int i = 0;i<array.length;i++){
                streamNames[i] = array[i].getName();
            }
        }
        return streamNames;
    }

    private void refreshStreamItems(){
        if(streamNames!=null){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,streamNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter.setNotifyOnChange(true);
            spinnerStreams.setAdapter(adapter);
            spinnerStreams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    streamId = streams[position].getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{
            Log.d("asdf","NULL ON STREAMNAMES");
        }
    }

    public void saveSettings(View view){
        ServerRequestHandler serverRequestHandler = new ServerRequestHandler(this);

        String url = "http://lipa.kvewijk.nl/android/alarm/edit?session="
                + serverRequestHandler.getLocalPreferences().getString("session","")
                + "&id=" + id
                + "&name=" + editTextName.getText().toString()
                + "&time=" + editTextTime.getText().toString()
                + "&stream=" + streamId;

        Log.d("url",url);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast("alarm edited");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("failed alarm edit");
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
