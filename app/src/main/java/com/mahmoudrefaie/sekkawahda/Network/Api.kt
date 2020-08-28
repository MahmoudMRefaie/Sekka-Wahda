package com.mahmoudrefaie.sekkawahda.Network

import com.google.gson.annotations.SerializedName
import com.mahmoudrefaie.sekkawahda.Pojo.*
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.More.MyTrips
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @POST("api/Account/Register")
    fun createUser(@Body user: User): Call<String>

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

    @GET("/api/HomePage/UserInfoToAccessProfile")
    fun getUserInfoToAccessProfile(@Header("Authorization") authToken: String?): Call<UserIdResponse>

    @GET("/api/Profile/GetProfile/{id}")
    fun getProfileDetails(
            @Path("id") id : Int?,
            @Header("Authorization") authToken: String?
    ) : Call<User>

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfileFullName(
            @Field("FullName") fullname: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfileCity(
            @Field("city") city: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfileEmail(
            @Field("UserEmailID") email: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfilePhone(
            @Field("PhoneNumber") phone: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfileCarModel(
            @Field("CarModel") carModel: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @Multipart
    @POST("api/Profile/UpdateProfile")
    fun updateProfileCarImage(
            @Part carImage: MultipartBody.Part?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfileDriverLicense(
            @Field("DriverLicense") carLicense: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/Profile/UpdateProfile")
    fun updateProfileCarLicense(
            @Field("CarLicense") carLicense: String?,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @FormUrlEncoded
    @POST("api/HomePage/PostTrip")
    fun postTrip(
            @Field("FromCity") fromCity : String,
            @Field("ToCity") toCity : String,
            @Field("PlaceToMeet") placeToMeet : String,
            @Field("DateOfTrip") tripDate : String,
            @Field("TimeOfTrip") tripTime : String,
            @Header("Authorization") authToken: String?
    ): Call<String>?

    @GET("api/HomePage/GetAllTrips")
    fun getTrips( @Header("Authorization") authToken: String? ): Call<List<Trip>>?

    @POST("api/Reservation/MakeResrvation")
    fun reserveTrip(
            @Query("TripID") tripId : String,
            @Header("Authorization") authToken: String?
    ): Call<String>

    @FormUrlEncoded
    @POST("api/HomePage/SearchForTrip")
    fun search(
            @Field("FromCity") fromCity: String,
            @Field("ToCity") toCity: String,
            @Field("DateOfTrip") dateOfTrip: String,
            @Header("Authorization") authToken: String?
    ): Call<List<Trip>>

    @GET("api/Notification/GetNotifications")
    fun notifications( @Header("Authorization") authToken: String?): Call<List<NotificationResponse>>

    @GET("api/HomePage/GetMyReservedTrips")
    fun myTrips( @Header("Authorization") authToken: String?): Call<List<Trip>>
}