package com.jannick.lipastreaming.activities.launch;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.activities.MainActivity;
import com.jannick.lipastreaming.handlers.ServerRequestHandler;
import com.jannick.lipastreaming.utils.LayoutUtils;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        LayoutUtils.changeStatusbarColor(this,R.color.colorPrimary);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 0.5 seconds
                    sleep(500);

                    // After 1 seconds redirect to another intent
                    loadActivity();

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }

    private void loadActivity(){
        SharedPreferences preferences = getSharedPreferences(ServerRequestHandler.SP_NAME,MODE_PRIVATE);
        if(preferences.getString("session","").isEmpty()){
            LayoutUtils.navigateToActivity(this, LoginActivity.class);
        }
        else{
            LayoutUtils.navigateToActivity(this, MainActivity.class);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
