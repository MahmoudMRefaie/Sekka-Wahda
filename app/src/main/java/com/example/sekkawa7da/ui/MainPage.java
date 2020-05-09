package com.example.sekkawa7da.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sekkawa7da.R;
import com.example.sekkawa7da.MainPageNavigationFragments.HomeFragment;
import com.example.sekkawa7da.MainPageNavigationFragments.MoreFragment;
import com.example.sekkawa7da.MainPageNavigationFragments.NotificationsFragment;
import com.example.sekkawa7da.MainPageNavigationFragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    BottomNavigationView topNav;
    //FrameLayout mainFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);

        topNav = findViewById(R.id.top_navigation);
        topNav.setOnNavigationItemSelectedListener(navListener);

        //To make HomeFragment is default
        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_more:
                            selectedFragment = new MoreFragment();
                            break;
                        case R.id.nav_navigation:
                            selectedFragment = new NotificationsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
