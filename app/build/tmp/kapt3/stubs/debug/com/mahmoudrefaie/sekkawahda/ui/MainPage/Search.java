package com.mahmoudrefaie.sekkawahda.ui.MainPage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005J\u0012\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000bH\u0002J\u001e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/Search;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "accessToken", "", "appAuth", "Landroid/content/SharedPreferences;", "mDateSetListener", "Landroid/app/DatePickerDialog$OnDateSetListener;", "getTrips", "", "fromCity", "toCity", "tripDate", "authToken", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setTripDate", "showTripData", "context", "Landroid/content/Context;", "trips", "", "Lcom/mahmoudrefaie/sekkawahda/Pojo/Trip;", "app_debug"})
public final class Search extends androidx.appcompat.app.AppCompatActivity implements android.view.View.OnClickListener {
    private android.content.SharedPreferences appAuth;
    private java.lang.String accessToken;
    private android.app.DatePickerDialog.OnDateSetListener mDateSetListener;
    private java.util.HashMap _$_findViewCache;
    
    public Search() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.Nullable
    android.view.View v) {
    }
    
    public final void getTrips(@org.jetbrains.annotations.NotNull
    java.lang.String fromCity, @org.jetbrains.annotations.NotNull
    java.lang.String toCity, @org.jetbrains.annotations.NotNull
    java.lang.String tripDate, @org.jetbrains.annotations.NotNull
    java.lang.String authToken) {
    }
    
    private final void showTripData(android.content.Context context, java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.Trip> trips) {
    }
    
    private final void setTripDate() {
    }
}