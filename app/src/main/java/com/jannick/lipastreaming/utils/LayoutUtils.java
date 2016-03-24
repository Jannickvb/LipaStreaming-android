package com.jannick.lipastreaming.utils;

/**
 * Created by Jannick on 17-3-2016.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class LayoutUtils {

    public static void navigateToActivity(Context context, Class<?> c){
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    public static void addNavigateToButtonListener(Context context,View v, Class<?> target){

        final Context c = context;
        final Class<?> t = target;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(c, t);
            }
        });
    }

    public static void changeStatusbarColor(Activity activity, int color){
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(ContextCompat.getColor(activity,color));
        }
    }

    public static void addBackButtonListener(Activity activity, View v){
        final Activity a = activity;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.onBackPressed();
            }
        });
    }

    public static void createFormInvalidDialog(Context context){
        createAlertDialog(context," ","Niet alle vereiste gegevens zijn ingevuld. Controleer of u alle gegevens heeft ingevoerd alvorens naar de volgende stap te gaan.");
    }

    public static void createAlertDialog(Context context, String title, String description){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(description);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}