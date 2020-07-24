package com.example.sekkawa7da.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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