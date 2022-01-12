package com.example.app_demo_chat.model

import com.google.gson.annotations.SerializedName


//import com.google.gson.annotations.SerializedName

data class Message(
//    @SerializedName("msgId")
    val msgId: Int,
//    @SerializedName("userId")
    val userId: Int,
   @SerializedName("text")
    val msg: String,
//    @SerializedName("date")
    val date: String
)