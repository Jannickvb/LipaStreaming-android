package com.jannick.lipastreaming.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.activities.MainActivity;
import com.jannick.lipastreaming.activities.edit.DeviceEditActivity;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.model.jsonTokens.DevicesToken;
import com.jannick.lipastreaming.utils.LayoutUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.util.PriorityQueue;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jannick on 17-3-2016.
 */
public class DeviceAdapter extends ArrayAdapter<DevicesToken.Device>{

    DevicesToken.Device[] devices;
    Activity activity;
    public DeviceAdapter(Context context, DevicesToken.Device[] devices,Activity activity){
        super(context, R.layout.list_row_device, devices);
        this.devices = devices;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.list_row_device, parent, false);

        final DevicesToken.Device item = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.device_name);
        TextView desc = (TextView)v.findViewById(R.id.device_desc);
        Button select = (Button)v.findViewById(R.id.list_row_select);
        Button edit = (Button)v.findViewById(R.id.list_row_edit);

        name.setText(item.getName());
        desc.setText(item.getDescription());

        if(item.isSelected()){
            select.setEnabled(false);
            select.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.colorPrimaryLight));
        }else{
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    ServerRequestHandler serverRequestHandler = new ServerRequestHandler(v.getContext());
                    String url = "http://lipa.kvewijk.nl/android/select_device.php?session="
                            +serverRequestHandler.getCurrentToken().getSession()
                            +"&device="
                            +item.getDevice();
                    client.get(v.getContext(), url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Log.d("Select Device", "Select Device Succes");
                            activity.onBackPressed();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.d("Select Device", "Select Device Failed");
                        }
                    });
                }
            });
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DeviceEditActivity.class);
                intent.putExtra("id",item.getDevice());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("location",item.getLocation());
                intent.putExtra("name",item.getName());
                v.getContext().startActivity(intent);
            }
        });



        return v;
    }
}
