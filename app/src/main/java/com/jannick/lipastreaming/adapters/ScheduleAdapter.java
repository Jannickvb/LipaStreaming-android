package com.jannick.lipastreaming.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.activities.edit.AlarmEditActivity;
import com.jannick.lipastreaming.activities.edit.ScheduleEditActivity;
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
public class ScheduleAdapter extends ArrayAdapter<SchedulerToken.Schedule>{

    SchedulerToken.Schedule[] schedules;

    public ScheduleAdapter(Context context, SchedulerToken.Schedule[] schedules){
        super(context, R.layout.list_row_schedule,schedules);
        this.schedules = schedules;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_schedule, parent, false);

        final SchedulerToken.Schedule item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.schedule_name);
        TextView time = (TextView)v.findViewById(R.id.schedule_time);
        TextView stream = (TextView)v.findViewById(R.id.schedule_stream);

        name.setText(item.getName());

        time.setText(TimeUtils.getTime(item.getTime()) + " - "
                + TimeUtils.addTime(item.getTime(),item.getDuration()));

        stream.setText("Stream: "+item.getStream());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScheduleEditActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("name", item.getName());
                intent.putExtra("time", item.getTime());
                intent.putExtra("stream", item.getStream());
                intent.putExtra("duration",item.getDuration());
                v.getContext().startActivity(intent);
            }
        });

        return v;
    }
}
