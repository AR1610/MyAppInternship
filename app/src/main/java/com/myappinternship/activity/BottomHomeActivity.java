package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myappinternship.R;
import com.myappinternship.fragments.GalleryFragment;
import com.myappinternship.fragments.HomeFragment;

public class BottomHomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_home);
        bottomNavigationView = findViewById(R.id.bottom_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.setting){

                    fragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");
                } else if (id == R.id.profile){

                    toolbar.setTitle("Gallery");
                    fragment = new GalleryFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();

                }

                return true;

            }
        });

    }
}