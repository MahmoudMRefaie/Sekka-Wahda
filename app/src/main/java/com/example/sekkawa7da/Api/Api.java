package com.example.sekkawa7da.Api;

import com.example.sekkawa7da.SharedPreferences.LoginResponse;
import com.example.sekkawa7da.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("api/Account/Register")
    public Call<String> createUser(@Body User user);

    @GET("api/Account/GetCurrentUser")
    Call<String> getInfo(@Body User user);

    @FormUrlEncoded
    @POST("token")
    Call<LoginResponse> getUserLogin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grant_type
    );
}
