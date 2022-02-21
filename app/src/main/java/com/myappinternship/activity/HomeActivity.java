package com.myappinternship.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.myappinternship.R;

public class HomeActivity extends AppCompatActivity {

    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvEmail = findViewById(R.id.tv_email);

        Intent i = getIntent();
        String strEmail = i.getStringExtra("KEY_EMAIL");
        tvEmail.setText(strEmail);

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();


        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("MyAppInternship");
        builder.setIcon(R.drawable.icon_2);
        builder.setMessage("Are you sure, you want to Back?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });


        builder.show();


    }
}