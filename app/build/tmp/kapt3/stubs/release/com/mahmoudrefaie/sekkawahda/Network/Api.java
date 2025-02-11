package com.mahmoudrefaie.sekkawahda.Network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001a\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\'J+\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'\u00a2\u0006\u0002\u0010\u000eJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J\"\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J2\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\'J \u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J \u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00110\u00032\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'JN\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u001e\u001a\u00020\u00042\b\b\u0001\u0010\u001f\u001a\u00020\u00042\b\b\u0001\u0010 \u001a\u00020\u00042\b\b\u0001\u0010!\u001a\u00020\u00042\b\b\u0001\u0010\"\u001a\u00020\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010$\u001a\u00020\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J>\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00032\b\b\u0001\u0010\u001e\u001a\u00020\u00042\b\b\u0001\u0010\u001f\u001a\u00020\u00042\b\b\u0001\u0010&\u001a\u00020\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u0010\'\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u0010(\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u0010*\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u0010,\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u0010.\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u0010*\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u00100\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u00101\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u00102\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u00103\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'J(\u00104\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0001\u00105\u001a\u0004\u0018\u00010\u00042\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\'\u00a8\u00066"}, d2 = {"Lcom/mahmoudrefaie/sekkawahda/Network/Api;", "", "addProfilePic", "Lretrofit2/Call;", "", "image", "Lokhttp3/MultipartBody$Part;", "authToken", "createUser", "user", "Lcom/mahmoudrefaie/sekkawahda/Pojo/User;", "getProfileDetails", "id", "", "(Ljava/lang/Integer;Ljava/lang/String;)Lretrofit2/Call;", "getProfilePic", "getTrips", "", "Lcom/mahmoudrefaie/sekkawahda/Pojo/Trip;", "getUserInfoToAccessProfile", "Lcom/mahmoudrefaie/sekkawahda/Pojo/UserIdResponse;", "getUserLogin", "Lcom/mahmoudrefaie/sekkawahda/Pojo/LoginResponse;", "username", "password", "grant_type", "myTrips", "notifications", "Lcom/mahmoudrefaie/sekkawahda/Pojo/NotificationResponse;", "postTrip", "fromCity", "toCity", "placeToMeet", "tripDate", "tripTime", "reserveTrip", "tripId", "search", "dateOfTrip", "updateProfileCarImage", "carImage", "updateProfileCarLicense", "carLicense", "updateProfileCarModel", "carModel", "updateProfileCity", "city", "updateProfileDriverLicense", "updateProfileEmail", "email", "updateProfileFullName", "fullname", "updateProfilePhone", "phone", "app_release"})
public abstract interface Api {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "api/Account/Register")
    public abstract retrofit2.Call<java.lang.String> createUser(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.mahmoudrefaie.sekkawahda.Pojo.User user);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "token")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<com.mahmoudrefaie.sekkawahda.Pojo.LoginResponse> getUserLogin(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "username")
    java.lang.String username, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "password")
    java.lang.String password, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "grant_type")
    java.lang.String grant_type);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Account/AddPhoto")
    @retrofit2.http.Multipart
    public abstract retrofit2.Call<java.lang.String> addProfilePic(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part image, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "api/Account/GetUserPhoto")
    public abstract retrofit2.Call<java.lang.String> getProfilePic(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "/api/HomePage/UserInfoToAccessProfile")
    public abstract retrofit2.Call<com.mahmoudrefaie.sekkawahda.Pojo.UserIdResponse> getUserInfoToAccessProfile(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "/api/Profile/GetProfile/{id}")
    public abstract retrofit2.Call<com.mahmoudrefaie.sekkawahda.Pojo.User> getProfileDetails(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Path(value = "id")
    java.lang.Integer id, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfileFullName(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "FullName")
    java.lang.String fullname, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfileCity(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "city")
    java.lang.String city, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfileEmail(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "UserEmailID")
    java.lang.String email, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfilePhone(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "PhoneNumber")
    java.lang.String phone, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfileCarModel(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "CarModel")
    java.lang.String carModel, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.Multipart
    public abstract retrofit2.Call<java.lang.String> updateProfileCarImage(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part carImage, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfileDriverLicense(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "DriverLicense")
    java.lang.String carLicense, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/Profile/UpdateProfile")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> updateProfileCarLicense(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "CarLicense")
    java.lang.String carLicense, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/HomePage/PostTrip")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.lang.String> postTrip(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "FromCity")
    java.lang.String fromCity, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "ToCity")
    java.lang.String toCity, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "PlaceToMeet")
    java.lang.String placeToMeet, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "DateOfTrip")
    java.lang.String tripDate, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "TimeOfTrip")
    java.lang.String tripTime, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/HomePage/GetAllTrips")
    public abstract retrofit2.Call<java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.Trip>> getTrips(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "api/Reservation/MakeResrvation")
    public abstract retrofit2.Call<java.lang.String> reserveTrip(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "TripID")
    java.lang.String tripId, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "api/HomePage/SearchForTrip")
    @retrofit2.http.FormUrlEncoded
    public abstract retrofit2.Call<java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.Trip>> search(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "FromCity")
    java.lang.String fromCity, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "ToCity")
    java.lang.String toCity, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "DateOfTrip")
    java.lang.String dateOfTrip, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "api/Notification/GetNotifications")
    public abstract retrofit2.Call<java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse>> notifications(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "api/HomePage/GetMyReservedTrips")
    public abstract retrofit2.Call<java.util.List<com.mahmoudrefaie.sekkawahda.Pojo.Trip>> myTrips(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String authToken);
}