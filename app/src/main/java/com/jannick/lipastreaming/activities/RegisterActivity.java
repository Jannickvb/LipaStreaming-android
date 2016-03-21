package com.jannick.lipastreaming.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.jannick.lipastreaming.R;
import com.jannick.lipastreaming.utils.LayoutUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        WebView webView = (WebView)findViewById(R.id.webview_register);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getString(R.string.url_register));

        LayoutUtils.changeStatusbarColor(this,R.color.colorPrimaryDark);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
