package com.jannick.lipastreaming.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;

/**
 * Created by Jannick on 17-3-2016.
 */
public class DeviceAdapter extends ArrayAdapter<DevicesToken.Device>{

    DevicesToken.Device[] devices;

    public DeviceAdapter(Context context, DevicesToken.Device[] devices){
        super(context, R.layout.list_row_device,devices);
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_device, parent, false);

        DevicesToken.Device item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.device_name);
        TextView desc = (TextView)v.findViewById(R.id.device_desc);

        name.setText(item.getName());
        desc.setText(item.getDescription());

        return v;
    }

}
