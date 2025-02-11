package com.mahmoudrefaie.sekkawahda.Network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/Network/RetrofitClient;", "", "()V", "api", "Lcom/mahmoudrefaie/sekkawahda/Network/Api;", "getApi", "()Lcom/mahmoudrefaie/sekkawahda/Network/Api;", "retrofit", "Lretrofit2/Retrofit;", "Companion", "app_release"})
public final class RetrofitClient {
    private final retrofit2.Retrofit retrofit = null;
    @org.jetbrains.annotations.NotNull
    public static final com.mahmoudrefaie.sekkawahda.Network.RetrofitClient.Companion Companion = null;
    private static final java.lang.String BASE_URL = "http://localhost:8080";
    private static com.mahmoudrefaie.sekkawahda.Network.RetrofitClient mInstance;
    
    private RetrofitClient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.mahmoudrefaie.sekkawahda.Network.Api getApi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.Synchronized
    public static final synchronized com.mahmoudrefaie.sekkawahda.Network.RetrofitClient getInstance() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00068FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/Network/RetrofitClient$Companion;", "", "()V", "BASE_URL", "", "instance", "Lcom/mahmoudrefaie/sekkawahda/Network/RetrofitClient;", "getInstance$annotations", "getInstance", "()Lcom/mahmoudrefaie/sekkawahda/Network/RetrofitClient;", "mInstance", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic
        @java.lang.Deprecated
        public static void getInstance$annotations() {
        }
        
        @org.jetbrains.annotations.Nullable
        @kotlin.jvm.Synchronized
        public final synchronized com.mahmoudrefaie.sekkawahda.Network.RetrofitClient getInstance() {
            return null;
        }
    }
}