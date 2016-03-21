package com.jannick.lipastreaming.activities.launch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jannick.lipastreaming.R;
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
                    // Thread will sleep for 1 seconds
                    sleep(1000);

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
        LayoutUtils.navigateToActivity(this, LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
