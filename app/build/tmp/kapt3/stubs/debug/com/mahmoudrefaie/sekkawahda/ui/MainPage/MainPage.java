package com.mahmoudrefaie.sekkawahda.ui.MainPage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/MainPage;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "count", "", "homeFragment", "Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Home/HomeFragment;", "moreFragment", "Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/More/MoreFragment;", "notificationsFragment", "Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Notifications/NotificationsFragment;", "searchIcon", "Lde/hdodenhof/circleimageview/CircleImageView;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "updateUI", "ViewPagerAdapter", "app_debug"})
public final class MainPage extends androidx.appcompat.app.AppCompatActivity {
    private de.hdodenhof.circleimageview.CircleImageView searchIcon;
    private androidx.appcompat.widget.Toolbar toolbar;
    private androidx.viewpager.widget.ViewPager viewPager;
    private com.google.android.material.tabs.TabLayout tabLayout;
    private com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.HomeFragment homeFragment;
    private com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationsFragment notificationsFragment;
    private com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.More.MoreFragment moreFragment;
    private int count = 0;
    private java.util.HashMap _$_findViewCache;
    
    public MainPage() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateUI() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\tJ\b\u0010\u0010\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u0005H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/MainPage$ViewPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "behavior", "", "(Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/MainPage;Landroidx/fragment/app/FragmentManager;I)V", "fragmentTitles", "", "", "fragments", "Landroidx/fragment/app/Fragment;", "addFragment", "", "fragment", "title", "getCount", "getItem", "position", "getPageTitle", "", "app_debug"})
    final class ViewPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
        private final java.util.List<androidx.fragment.app.Fragment> fragments = null;
        private final java.util.List<java.lang.String> fragmentTitles = null;
        
        public ViewPagerAdapter(@org.jetbrains.annotations.NotNull
        androidx.fragment.app.FragmentManager fm, int behavior) {
            super(null);
        }
        
        public final void addFragment(@org.jetbrains.annotations.NotNull
        androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull
        java.lang.String title) {
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public androidx.fragment.app.Fragment getItem(int position) {
            return null;
        }
        
        @java.lang.Override
        public int getCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable
        @java.lang.Override
        public java.lang.CharSequence getPageTitle(int position) {
            return null;
        }
    }
}