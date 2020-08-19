package com.mahmoudrefaie.sekkawahda.Pojo

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("Message_")
    val message : String? = null,

    @SerializedName("TypeOfNotification")
    val notificationType : String? = null,

    @SerializedName("RaiserID")
    val raiserId : Int? = null,

    @SerializedName("TripID")
    val tripId : Int? = null
)