package com.mahmoudrefaie.sekkawahda.ui.MainPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mahmoudrefaie.sekkawahda.R;
import com.mahmoudrefaie.sekkawahda.ui.Login.Login;
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.HomeFragment;
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.MoreFragment;
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.NotificationsFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    RelativeLayout relativeLayout;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private HomeFragment homeFragment;
    private NotificationsFragment notificationsFragment;
    private MoreFragment moreFragment;

    //private NotificationBadge notifyBadge;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sekka Wahda");
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        homeFragment = new HomeFragment();
        notificationsFragment = new NotificationsFragment();
        moreFragment = new MoreFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager() , 0);
        viewPagerAdapter.addFragment(homeFragment , "Home");
        viewPagerAdapter.addFragment(notificationsFragment , "Notifications");
        viewPagerAdapter.addFragment(moreFragment , "More");
        viewPager.setAdapter(viewPagerAdapter);

        //To Add Icons
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_24dp);
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_none_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_menu_black_24dp);

        //Notification Badge
        BadgeDrawable notificationBadgeDrawable = tabLayout.getTabAt(1).getOrCreateBadge();
        notificationBadgeDrawable.setVisible(true);
        notificationBadgeDrawable.setNumber(++count);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_selected_notifications_24dp);
                        //notifyBadge.clear();
                        notificationBadgeDrawable.setVisible(false);
                        break;
                    case 2:
                        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
                        break;
                    default:
                        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_none_black_24dp);
                        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_none_black_24dp);
                        break;
                    case 2:
                        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN);
                        break;
                    default:
                        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_none_black_24dp);
                        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN);
                        break;
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment , String title){
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    private void updateUI() {
        Toast.makeText(this,"You should login" , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

}
