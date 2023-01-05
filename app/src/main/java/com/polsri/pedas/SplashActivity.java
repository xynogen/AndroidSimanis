package com.polsri.pedas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    int DURATION = 3000;

    public boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected(); // isConnectedOrConnecting()
        return isConnected;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView StatusTV = (TextView) findViewById(R.id.statusTV);

        // 600 ms
        ConstraintLayout appTitle = (ConstraintLayout) findViewById(R.id.appTitle);
        Animation slide = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        slide.setDuration(DURATION - 2400);
        appTitle.setAnimation(slide);


        // 600 - 1400
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StatusTV.setText("Checking Internet Status");
                StatusTV.setVisibility(View.VISIBLE);
            }
        }, DURATION - 1600);


        // 1400 - 2200
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected(SplashActivity.this)) {
                    StatusTV.setText("Internet is Connected");

                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    StatusTV.setText("No Internet or Wifi");
                }
            }
        }, DURATION - 800);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected(SplashActivity.this)) {
                    StatusTV.setText("Closing Aplication");
                    finish();
                }
            }
        }, DURATION);
    }


}