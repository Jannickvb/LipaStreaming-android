package com.jannick.lipastreaming.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.model.DeviceItem;
import com.jannick.lipastreaming.model.StreamItem;

/**
 * Created by Jannick on 17-3-2016.
 */
public class DeviceAdapter extends ArrayAdapter<DeviceItem>{

    DeviceItem[] devices;

    public DeviceAdapter(Context context, DeviceItem[] devices){
        super(context, R.layout.list_row_device,devices);
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_device, parent, false);

        DeviceItem item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.device_name);
        TextView desc = (TextView)v.findViewById(R.id.device_desc);

        name.setText(item.getName());
        desc.setText(item.getDesc());

        return v;
    }

}
