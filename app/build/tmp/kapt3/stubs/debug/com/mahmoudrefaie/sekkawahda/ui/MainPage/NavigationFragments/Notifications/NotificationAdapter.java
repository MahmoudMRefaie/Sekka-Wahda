package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0013B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Notifications/NotificationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Notifications/NotificationAdapter$NotificationsHolder;", "context", "Landroid/content/Context;", "notifications", "", "Lcom/mahmoudrefaie/sekkawahda/Pojo/NotificationResponse;", "(Landroid/content/Context;Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "NotificationsHolder", "app_debug"})
public final class NotificationAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationAdapter.NotificationsHolder> {
    private final android.content.Context context = null;
    private final java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse> notifications = null;
    
    public NotificationAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse> notifications) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationAdapter.NotificationsHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationAdapter.NotificationsHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019\u00a8\u0006\""}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Notifications/NotificationAdapter$NotificationsHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/mahmoudrefaie/sekkawahda/ui/MainPage/NavigationFragments/Notifications/NotificationAdapter;Landroid/view/View;)V", "accessToken", "", "appAuth", "Landroid/content/SharedPreferences;", "notificationMessage", "Landroid/widget/TextView;", "getNotificationMessage", "()Landroid/widget/TextView;", "setNotificationMessage", "(Landroid/widget/TextView;)V", "notificationType", "getNotificationType", "()Ljava/lang/String;", "setNotificationType", "(Ljava/lang/String;)V", "raiserId", "", "getRaiserId", "()Ljava/lang/Integer;", "setRaiserId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "tripId", "getTripId", "setTripId", "bindView", "", "notificationItem", "Lcom/mahmoudrefaie/sekkawahda/Pojo/NotificationResponse;", "app_debug"})
    public final class NotificationsHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.Nullable
        private android.widget.TextView notificationMessage;
        @org.jetbrains.annotations.Nullable
        private java.lang.String notificationType;
        @org.jetbrains.annotations.Nullable
        private java.lang.Integer raiserId;
        @org.jetbrains.annotations.Nullable
        private java.lang.Integer tripId;
        private java.lang.String accessToken;
        private android.content.SharedPreferences appAuth;
        
        public NotificationsHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable
        public final android.widget.TextView getNotificationMessage() {
            return null;
        }
        
        public final void setNotificationMessage(@org.jetbrains.annotations.Nullable
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getNotificationType() {
            return null;
        }
        
        public final void setNotificationType(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Integer getRaiserId() {
            return null;
        }
        
        public final void setRaiserId(@org.jetbrains.annotations.Nullable
        java.lang.Integer p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Integer getTripId() {
            return null;
        }
        
        public final void setTripId(@org.jetbrains.annotations.Nullable
        java.lang.Integer p0) {
        }
        
        public final void bindView(@org.jetbrains.annotations.NotNull
        com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse notificationItem) {
        }
    }
}