package com.example.sekkawa7da.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sekkawa7da.R;
import com.example.sekkawa7da.MainPageNavigationFragments.HomeFragment;
import com.example.sekkawa7da.MainPageNavigationFragments.MoreFragment;
import com.example.sekkawa7da.MainPageNavigationFragments.NotificationsFragment;
import com.google.android.material.tabs.TabLayout;
import com.nex3z.notificationbadge.NotificationBadge;

import org.xmlpull.v1.XmlPullParser;

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
        relativeLayout = findViewById(R.id.tab_lay_relative);
        NotificationBadge notifyBadge = new NotificationBadge(this, null);  //Create notification badge
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(60,60); //set attributes for this badge
        params.leftMargin = 540;
        relativeLayout.addView(notifyBadge, params);
        notifyBadge.setNumber(++count); //number in this badge

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_selected_notifications_24dp);
                        notifyBadge.clear();
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

        //BadgeDrawable badgeDrawable;

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

}
