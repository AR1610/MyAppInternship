package com.myappinternship.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.myappinternship.R;
import com.myappinternship.fragments.ContactUsFragment;
import com.myappinternship.fragments.GalleryFragment;
import com.myappinternship.fragments.HomeFragment;

public class NavHomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        //setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_Internship", MODE_PRIVATE);
        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");


        View headerView = navigationView.getHeaderView(0);
        TextView tvEmail = headerView.findViewById(R.id.tv_email);
        tvEmail.setText(strEmail);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        loadDashboard();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.nav_home) {

                    fragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");
                } else if (id == R.id.nav_gallery) {

                    toolbar.setTitle("Gallery");
                    fragment = new GalleryFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                } else if (id == R.id.nav_contact_us) {

                    toolbar.setTitle("Contact Us");
                    fragment = new ContactUsFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                }else  if(id == R.id.item_logout){

                    SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_Internship",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("KEY_PREF_EMAIL");
                    editor.remove("KEY_PREF_Password");
                    editor.commit();
                    Intent i = new Intent(NavHomeActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void loadDashboard() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
        toolbar.setTitle("Home");

    }
}