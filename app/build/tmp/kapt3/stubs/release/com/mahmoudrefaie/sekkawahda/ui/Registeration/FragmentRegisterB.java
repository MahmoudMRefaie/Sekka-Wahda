package com.mahmoudrefaie.sekkawahda.ui.Registeration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u001c\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002J>\u0010\u001c\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0012\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u001aH\u0002J\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0016J&\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u0006\u0010)\u001a\u00020\u0018J\b\u0010*\u001a\u00020\u0018H\u0002J\b\u0010+\u001a\u00020\u0014H\u0002J\b\u0010,\u001a\u00020\u0014H\u0002J\b\u0010-\u001a\u00020\u0014H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/Registeration/FragmentRegisterB;", "Landroidx/fragment/app/Fragment;", "Landroid/view/View$OnClickListener;", "()V", "city", "Landroid/widget/Spinner;", "isAgree", "Landroid/widget/CheckBox;", "loginResponse", "Lcom/mahmoudrefaie/sekkawahda/Pojo/LoginResponse;", "phone_no", "Lcom/google/android/material/textfield/TextInputLayout;", "previous", "Landroid/widget/TextView;", "registerBtn", "Landroid/widget/Button;", "sharedPre", "Landroid/content/SharedPreferences;", "ssn", "isValidPhone", "", "phone", "", "makeLogin", "", "user", "", "pass", "makeRegister", "username", "email", "onClick", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "openHomeActivity", "openLoginPage", "validateCity", "validatePhoneNo", "validateSSN", "app_release"})
public final class FragmentRegisterB extends androidx.fragment.app.Fragment implements android.view.View.OnClickListener {
    private com.google.android.material.textfield.TextInputLayout ssn;
    private com.google.android.material.textfield.TextInputLayout phone_no;
    private android.widget.Spinner city;
    private android.widget.CheckBox isAgree;
    private android.widget.TextView previous;
    private android.widget.Button registerBtn;
    private com.mahmoudrefaie.sekkawahda.Pojo.LoginResponse loginResponse;
    private android.content.SharedPreferences sharedPre;
    private java.util.HashMap _$_findViewCache;
    
    public FragmentRegisterB() {
        super();
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
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View view) {
    }
    
    private final void makeRegister(java.lang.String username, java.lang.String email, java.lang.String pass, java.lang.String ssn, java.lang.String phone, java.lang.String city) {
    }
    
    private final void makeLogin(java.lang.String user, java.lang.String pass) {
    }
    
    public final void openHomeActivity() {
    }
    
    private final boolean validateSSN() {
        return false;
    }
    
    private final boolean validatePhoneNo() {
        return false;
    }
    
    public final boolean isValidPhone(@org.jetbrains.annotations.Nullable
    java.lang.CharSequence phone) {
        return false;
    }
    
    private final boolean validateCity() {
        return false;
    }
    
    private final void openLoginPage() {
    }
}