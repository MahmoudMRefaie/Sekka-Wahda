package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Notifications/NotificationsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "accessToken", "", "appAuth", "Landroid/content/SharedPreferences;", "recycler", "Landroidx/recyclerview/widget/RecyclerView;", "getRecycler", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecycler", "(Landroidx/recyclerview/widget/RecyclerView;)V", "refreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "getNotification", "", "authToken", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "showNotification", "context", "Landroid/content/Context;", "notificationData", "", "Lcom/mahmoudrefaie/sekkawahda/Pojo/NotificationResponse;", "app_release"})
public final class NotificationsFragment extends androidx.fragment.app.Fragment {
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout refreshLayout;
    private android.content.SharedPreferences appAuth;
    private java.lang.String accessToken;
    public androidx.recyclerview.widget.RecyclerView recycler;
    private java.util.HashMap _$_findViewCache;
    
    public NotificationsFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.recyclerview.widget.RecyclerView getRecycler() {
        return null;
    }
    
    public final void setRecycler(@org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public final void getNotification(@org.jetbrains.annotations.NotNull
    java.lang.String authToken) {
    }
    
    private final void showNotification(android.content.Context context, java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse> notificationData) {
    }
}