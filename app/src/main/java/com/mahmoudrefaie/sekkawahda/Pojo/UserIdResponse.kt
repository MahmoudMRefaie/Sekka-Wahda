package com.mahmoudrefaie.sekkawahda.Pojo

import com.google.gson.annotations.SerializedName

data class UserIdResponse(
        @SerializedName("name")
        var name:String?=null,
        @SerializedName("UserID")
        var userId:Int?=0,
        @SerializedName("ImageUrl")
        var profileImageUrl:String?=null
)