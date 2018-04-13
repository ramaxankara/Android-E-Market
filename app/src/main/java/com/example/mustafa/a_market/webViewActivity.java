package com.example.mustafa.a_market;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;


/**
 * Created by Ramazan Kara on 3.03.2018.
 */

public class webViewActivity extends AppCompatActivity {

    public WebView webView;

    public String marketName="Mişaş";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView=findViewById(R.id.webView);


        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl(" https://www.google.com.tr/maps/search/" + marketName);

        //google maps url si alındı

    }
}
