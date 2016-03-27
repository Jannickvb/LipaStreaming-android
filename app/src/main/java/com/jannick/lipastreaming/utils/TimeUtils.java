package com.jannick.lipastreaming.utils;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jannick on 27-3-2016.
 */
public class TimeUtils {

    public static String getTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("hh:mm");

        return sdf.format(d);
    }

    public static String addTime(String starttime, String duration){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date d = null;
        Date d2 = null;
        try {
            d = sdf.parse(starttime);
            d2 = sdf.parse(duration);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("HH:mm");
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        DateTime dateTime = new DateTime(d2);
        c.add(Calendar.HOUR_OF_DAY,dateTime.getHourOfDay());
        c.add(Calendar.MINUTE,dateTime.getMinuteOfHour());

        return sdf.format(c.getTime());
    }

}
