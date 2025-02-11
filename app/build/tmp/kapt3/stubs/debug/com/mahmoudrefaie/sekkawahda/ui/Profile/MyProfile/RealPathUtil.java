package com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/Profile/MyProfile/RealPathUtil;", "", "()V", "Companion", "app_debug"})
public final class RealPathUtil {
    @org.jetbrains.annotations.NotNull
    public static final com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile.RealPathUtil.Companion Companion = null;
    
    public RealPathUtil() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J9\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\bJ\u001c\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0007J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\u0018"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/ui/Profile/MyProfile/RealPathUtil$Companion;", "", "()V", "getDataColumn", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getRealPath", "fileUri", "getRealPathFromURI_API11to18", "contentUri", "getRealPathFromURI_API19", "getRealPathFromURI_BelowAPI11", "isDownloadsDocument", "", "isExternalStorageDocument", "isGooglePhotosUri", "isMediaDocument", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getRealPath(@org.jetbrains.annotations.Nullable
        android.content.Context context, @org.jetbrains.annotations.Nullable
        android.net.Uri fileUri) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @android.annotation.SuppressLint(value = {"NewApi"})
        public final java.lang.String getRealPathFromURI_API11to18(@org.jetbrains.annotations.Nullable
        android.content.Context context, @org.jetbrains.annotations.Nullable
        android.net.Uri contentUri) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getRealPathFromURI_BelowAPI11(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.Nullable
        android.net.Uri contentUri) {
            return null;
        }
        
        /**
         * Get a file path from a Uri. This will get the the path for Storage Access
         * Framework Documents, as well as the _data field for the MediaStore and
         * other file-based ContentProviders.
         *
         * @param context The context.
         * @param uri     The Uri to query.
         * @author paulburke
         */
        @org.jetbrains.annotations.NotNull
        @android.annotation.SuppressLint(value = {"NewApi"})
        public final java.lang.String getRealPathFromURI_API19(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        android.net.Uri uri) {
            return null;
        }
        
        /**
         * Get the value of the data column for this Uri. This is useful for
         * MediaStore Uris, and other file-based ContentProviders.
         *
         * @param context       The context.
         * @param uri           The Uri to query.
         * @param selection     (Optional) Filter used in the query.
         * @param selectionArgs (Optional) Selection arguments used in the query.
         * @return The value of the _data column, which is typically a file path.
         */
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getDataColumn(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.Nullable
        android.net.Uri uri, @org.jetbrains.annotations.Nullable
        java.lang.String selection, @org.jetbrains.annotations.Nullable
        java.lang.String[] selectionArgs) {
            return null;
        }
        
        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        public final boolean isExternalStorageDocument(@org.jetbrains.annotations.NotNull
        android.net.Uri uri) {
            return false;
        }
        
        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        public final boolean isDownloadsDocument(@org.jetbrains.annotations.NotNull
        android.net.Uri uri) {
            return false;
        }
        
        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        public final boolean isMediaDocument(@org.jetbrains.annotations.NotNull
        android.net.Uri uri) {
            return false;
        }
        
        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is Google Photos.
         */
        public final boolean isGooglePhotosUri(@org.jetbrains.annotations.NotNull
        android.net.Uri uri) {
            return false;
        }
    }
}