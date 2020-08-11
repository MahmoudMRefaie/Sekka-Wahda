package com.mahmoudrefaie.sekkawahda.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val retrofit: Retrofit
    val api: Api
        get() = retrofit.create(Api::class.java)

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    companion object {
        private const val BASE_URL = "https://seka.azurewebsites.net/"
        //private static final String BASE_URL = "https://sekkawahdaa.azurewebsites.net/";
        //private static final String BASE_URL = "https://sekkawahda.somee.com/";
        private var mInstance: RetrofitClient? = null
        @JvmStatic
        @get:Synchronized
        val instance: RetrofitClient?
            get() {
                if (mInstance == null) {
                    mInstance = RetrofitClient()
                }
                return mInstance
            }
    }
}

/*
public class RetrofitClient {
    private static final String BASE_URL = "https://seka.azurewebsites.net/";
    //private static final String BASE_URL = "https://sekkawahdaa.azurewebsites.net/";
    //private static final String BASE_URL = "https://sekkawahda.somee.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
*/