package com.polsri.pedas;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OfflineActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

         webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        String url = "http://192.168.1.63:8082/api/camera/capture?api_key=dca767e7-308d-4fcd-8f82-6c9766f3940b";
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.loadUrl(url);
                        Toast toast = Toast.makeText(OfflineActivity.this, "Page Refreshed", Toast.LENGTH_LONG);
                        toast.show();
                        swipe.setRefreshing(false);
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}