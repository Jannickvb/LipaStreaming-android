package com.jannick.lipastreaming.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.activities.AlarmActivity;
import com.jannick.lipastreaming.activities.DeviceActivity;
import com.jannick.lipastreaming.activities.SchedulerActivity;
import com.jannick.lipastreaming.activities.StreamActivity;
import com.jannick.lipastreaming.model.jsonTokens.AccountToken;
import com.jannick.lipastreaming.utils.LayoutUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jannick on 17-3-2016.
 */
public final class GridAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;
    private AccountToken accountToken;

    public GridAdapter(Context context,AccountToken accountToken) {
        this.accountToken = accountToken;
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Devices",       R.drawable.ic_speaker_48dp));
        mItems.add(new Item("Streams",   R.drawable.ic_radio_48dp));
        mItems.add(new Item("Alarms", R.drawable.ic_alarm_48dp));
        mItems.add(new Item("Scheduler",      R.drawable.ic_event_note_48dp));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView itemname;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.gridpicture, v.findViewById(R.id.gridpicture));
            v.setTag(R.id.gridpicturetext, v.findViewById(R.id.gridpicturetext));
        }

        picture = (ImageView) v.getTag(R.id.gridpicture);
        itemname = (TextView) v.getTag(R.id.gridpicturetext);

        final Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        itemname.setText(item.name);

        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = item.name;
                switch(s){
                    case "Devices":
                        LayoutUtils.navigateToActivity(v.getContext(), DeviceActivity.class);
                        break;
                    case "Streams":
                        Intent intent = new Intent(v.getContext(),StreamActivity.class);
                        intent.putExtra("device",accountToken.getDevice());
                        v.getContext().startActivity(intent);
                        break;
                    case "Alarms":
                        LayoutUtils.navigateToActivity(v.getContext(), AlarmActivity.class);
                        break;
                    case "Scheduler":
                        LayoutUtils.navigateToActivity(v.getContext(), SchedulerActivity.class);
                        break;
                    default:
                        break;
                }
            }

        });

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}
