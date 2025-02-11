package com.mahmoudrefaie.sekkawahda.ui.Profile.EditBottomSheetDialogs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J&\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0005H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/Profile/EditBottomSheetDialogs/EditPhoneBottomSheetDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "Landroid/view/View$OnClickListener;", "()V", "accessToken", "", "appAuth", "Landroid/content/SharedPreferences;", "bottomSheetListener", "Lcom/mahmoudrefaie/sekkawahda/ui/Profile/EditBottomSheetDialogs/EditPhoneBottomSheetDialog$BottomSheetListener;", "cancel", "Landroid/widget/Button;", "editPhone", "Lcom/google/android/material/textfield/TextInputLayout;", "save", "isValidPhone", "", "phone", "", "onAttach", "", "context", "Landroid/content/Context;", "onClick", "p0", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "updatePhone", "newPhoneNumber", "token", "validatePhoneNo", "BottomSheetListener", "app_release"})
public final class EditPhoneBottomSheetDialog extends com.google.android.material.bottomsheet.BottomSheetDialogFragment implements android.view.View.OnClickListener {
    private com.mahmoudrefaie.sekkawahda.ui.Profile.EditBottomSheetDialogs.EditPhoneBottomSheetDialog.BottomSheetListener bottomSheetListener;
    private com.google.android.material.textfield.TextInputLayout editPhone;
    private android.widget.Button cancel;
    private android.widget.Button save;
    private android.content.SharedPreferences appAuth;
    private java.lang.String accessToken;
    private java.util.HashMap _$_findViewCache;
    
    public EditPhoneBottomSheetDialog() {
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
    public void onClick(@org.jetbrains.annotations.Nullable
    android.view.View p0) {
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    private final void updatePhone(java.lang.String newPhoneNumber, java.lang.String token) {
    }
    
    private final boolean validatePhoneNo() {
        return false;
    }
    
    public final boolean isValidPhone(@org.jetbrains.annotations.Nullable
    java.lang.CharSequence phone) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/Profile/EditBottomSheetDialogs/EditPhoneBottomSheetDialog$BottomSheetListener;", "", "onEditPhoneButtonClicked", "", "text", "", "app_release"})
    public static abstract interface BottomSheetListener {
        
        public abstract void onEditPhoneButtonClicked(@org.jetbrains.annotations.NotNull
        java.lang.String text);
    }
}