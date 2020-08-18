package com.mahmoudrefaie.sekkawahda.Pojo

import com.google.gson.annotations.SerializedName

data class Trip(
        @SerializedName("DriverId")
        val driverId : Int,

        @SerializedName("ID")
        val tripId : Int,

        @SerializedName("Name")
        val driverName : String,

        @SerializedName("ImageUrl")
        val driverImageUrl : String,

        @SerializedName("FromCity")
        val fromCity : String,

        @SerializedName("ToCity")
        val toCity : String,

        @SerializedName("PlaceToMeet")
        val placeToMeet : String,

        @SerializedName("DateOfTrip")
        val tripDate : String,

        @SerializedName("TimeOfTrip")
        val tripTime : String,

        @SerializedName("PostTime")
        val postTime : PostTime
){
        data class PostTime(
                @SerializedName("time")
                val time : Int,
                @SerializedName("unit")
                val unit : String
        )
}
