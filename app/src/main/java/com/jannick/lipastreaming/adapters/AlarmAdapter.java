package com.jannick.lipastreaming.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.activities.edit.AlarmEditActivity;
import com.jannick.lipastreaming.activities.edit.DeviceEditActivity;
import com.jannick.lipastreaming.model.jsonTokens.AlarmToken;
import com.jannick.lipastreaming.model.jsonTokens.SchedulerToken;
import com.jannick.lipastreaming.model.jsonTokens.StreamToken;
import com.jannick.lipastreaming.utils.LinkUtils;
import com.jannick.lipastreaming.utils.TimeUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jannick on 17-3-2016.
 */
public class AlarmAdapter extends ArrayAdapter<AlarmToken.Alarm>{

    AlarmToken.Alarm[] alarms;

    public AlarmAdapter(Context context, AlarmToken.Alarm[] alarms){
        super(context, R.layout.list_row_alarm,alarms);
        this.alarms = alarms;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_alarm, parent, false);

        final AlarmToken.Alarm item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.alarm_name);
        TextView time = (TextView)v.findViewById(R.id.alarm_time);
        TextView stream = (TextView)v.findViewById(R.id.alarm_stream);

        name.setText(item.getName());

        time.setText(TimeUtils.getTime(item.getTime()));

        stream.setText("Stream: "+item.getStream());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlarmEditActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("name", item.getName());
                intent.putExtra("time", item.getTime());
                intent.putExtra("stream", item.getStream());
                v.getContext().startActivity(intent);
            }
        });

        return v;
    }

    public void deleteAlarm(View view){

    }
}
