package com.jannick.lipastreaming.utils;

/**
 * Created by Jannick on 17-3-2016.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;

/**
 * Created by jannick on 17-3-2016.
 */
public class LinkUtils {

    public LinkUtils(){

    }

    public static void addHyperlinkEvent(View v,String url){
        final String link = url;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                v.getContext().startActivity(browserIntent);
            }
        });
    }

    public static void launchHyperlink(View view, String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(browserIntent);
    }

    public static void addRegularMailEvent(View v, String email){
        final String link = email;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{link});

                /* Send it off to the Activity-Chooser */
                v.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });
    }
    /*
     * Desc: Add call event to view
     * Note: Uncomment method when needed(Add permission to manifest)
     *
    public static void addCallEvent(View v, String phoneNumber){
        final String number = phoneNumber;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = v.getContext().getPackageManager();
                int hasPerm = pm.checkPermission(
                        Manifest.permission.CALL_PHONE,
                        v.getContext().getPackageName());
                if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                    v.getContext().startActivity(intent);
                }
            }
        });

    }
    */
}
