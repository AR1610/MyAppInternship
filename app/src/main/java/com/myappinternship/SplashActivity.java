package com.myappinternship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    int time = 3000;
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gifImageView = findViewById(R.id.img_gif);
        gifImageView.setGifImageResource(R.drawable.android);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent i = new Intent(SplashActivity.this,SpinnerActivity.class);
            startActivity(i);
            finish();
        }
    },time);
    }
}