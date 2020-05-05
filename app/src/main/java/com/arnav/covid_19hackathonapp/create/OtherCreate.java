package com.arnav.covid_19hackathonapp.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arnav.covid_19hackathonapp.DonationSitesList;
import com.arnav.covid_19hackathonapp.R;

public class OtherCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_create);
        WebView webView = (WebView) findViewById(R.id.other_create);

        webView.setWebViewClient(new OtherCreate.MyWebViewClient());
        String url = "https://enable.hp.com/us-en-3dprint-COVID-19-containment-applications";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
