package com.example.sekkawa7da.Api

import com.example.sekkawa7da.Model.LoginResponse
import com.example.sekkawa7da.Model.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @POST("api/Account/Register")
    fun createUser(@Body user: User): Call<String>

    @GET("api/Account/GetCurrentUser")
    fun getInfo(@Body user: User?): Call<String?>?

    @FormUrlEncoded
    @POST("token")
    fun getUserLogin(
            @Field("username") username: String?,
            @Field("password") password: String?,
            @Field("grant_type") grant_type: String?
    ): Call<LoginResponse>

    @Multipart
    @POST("api/Account/AddPhoto")
    fun addProfilePic(
            @Part image: MultipartBody.Part?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @GET("api/Account/GetUserPhoto")
    fun getProfilePic(@Header("Authorization") authToken: String?): Call<String>
}