package com.myappinternship.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.myappinternship.utils.GifImageView;
import com.myappinternship.R;

public class SplashActivity extends AppCompatActivity {

    int time = 3000;
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gifImageView = findViewById(R.id.img_gif);
        gifImageView.setGifImageResource(R.drawable.android);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_Internship", MODE_PRIVATE);
        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");


        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(SplashActivity.this,CatActivity.class);
            startActivity(intent);
            finish();

     /*   if (strEmail.equals("")){
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);
            finish();

        }else {
            Intent i = new Intent(SplashActivity.this,NavHomeActivity.class);
            startActivity(i);
            finish();

        }*/
                    }
    },time);
    }
}