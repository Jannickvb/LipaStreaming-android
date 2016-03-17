package com.jannick.lipastreaming.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.model.StreamItem;

import org.w3c.dom.Text;

/**
 * Created by Jannick on 17-3-2016.
 */
public class StreamAdapter extends ArrayAdapter<StreamItem>{

    StreamItem[] streams;

    public StreamAdapter(Context context, StreamItem[] streams){
        super(context, R.layout.list_row_stream,streams);
        this.streams = streams;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_stream, parent, false);

        StreamItem item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.stream_name);
        TextView ip = (TextView)v.findViewById(R.id.stream_ip);

        name.setText(item.getName());
        ip.setText(item.getIp());

        return v;
    }
}
