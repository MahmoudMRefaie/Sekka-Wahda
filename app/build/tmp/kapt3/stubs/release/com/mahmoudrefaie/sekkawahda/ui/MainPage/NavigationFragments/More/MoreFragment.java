package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.More;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0012\u0010$\u001a\u00020\"2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J&\u0010\'\u001a\u0004\u0018\u00010&2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/More/MoreFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/view/View$OnClickListener;", "()V", "accessToken", "", "appAuth", "Landroid/content/SharedPreferences;", "logout", "Landroid/widget/RelativeLayout;", "getLogout", "()Landroid/widget/RelativeLayout;", "setLogout", "(Landroid/widget/RelativeLayout;)V", "myTrips", "getMyTrips", "setMyTrips", "profile", "getProfile", "setProfile", "profileName", "Landroid/widget/TextView;", "getProfileName", "()Landroid/widget/TextView;", "setProfileName", "(Landroid/widget/TextView;)V", "profilePic", "Lde/hdodenhof/circleimageview/CircleImageView;", "getProfilePic", "()Lde/hdodenhof/circleimageview/CircleImageView;", "setProfilePic", "(Lde/hdodenhof/circleimageview/CircleImageView;)V", "sharedPre", "getUserImageandName", "", "authToken", "onClick", "v", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_release"})
public final class MoreFragment extends androidx.fragment.app.Fragment implements android.view.View.OnClickListener {
    public de.hdodenhof.circleimageview.CircleImageView profilePic;
    public android.widget.TextView profileName;
    public android.widget.RelativeLayout profile;
    public android.widget.RelativeLayout myTrips;
    public android.widget.RelativeLayout logout;
    private android.content.SharedPreferences sharedPre;
    private android.content.SharedPreferences appAuth;
    private java.lang.String accessToken;
    private java.util.HashMap _$_findViewCache;
    
    public MoreFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final de.hdodenhof.circleimageview.CircleImageView getProfilePic() {
        return null;
    }
    
    public final void setProfilePic(@org.jetbrains.annotations.NotNull
    de.hdodenhof.circleimageview.CircleImageView p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.widget.TextView getProfileName() {
        return null;
    }
    
    public final void setProfileName(@org.jetbrains.annotations.NotNull
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.widget.RelativeLayout getProfile() {
        return null;
    }
    
    public final void setProfile(@org.jetbrains.annotations.NotNull
    android.widget.RelativeLayout p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.widget.RelativeLayout getMyTrips() {
        return null;
    }
    
    public final void setMyTrips(@org.jetbrains.annotations.NotNull
    android.widget.RelativeLayout p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.widget.RelativeLayout getLogout() {
        return null;
    }
    
    public final void setLogout(@org.jetbrains.annotations.NotNull
    android.widget.RelativeLayout p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.Nullable
    android.view.View v) {
    }
    
    private final void getUserImageandName(java.lang.String authToken) {
    }
}