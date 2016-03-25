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
import com.jannick.lipastreaming.model.jsonTokens.StreamToken;
import com.jannick.lipastreaming.utils.LinkUtils;

/**
 * Created by Jannick on 17-3-2016.
 */
public class StreamAdapter extends ArrayAdapter<StreamToken.Stream>{

    StreamToken.Stream[] streams;

    public StreamAdapter(Context context, StreamToken.Stream[] streams){
        super(context, R.layout.list_row_stream,streams);
        this.streams = streams;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_stream, parent, false);

        final StreamToken.Stream item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.stream_name);
        TextView ip = (TextView)v.findViewById(R.id.stream_ip);
        Button btTry = (Button)v.findViewById(R.id.list_row_try);

        name.setText(item.getName());
        ip.setText(item.getIp());
        String link = "http://"+item.getIp() + "/" + item.getPath();
        LinkUtils.addHyperlinkEvent(btTry,link);


        return v;
    }
}
