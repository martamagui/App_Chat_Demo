package com.example.app_demo_chat.model

import com.google.gson.annotations.SerializedName


//import com.google.gson.annotations.SerializedName

data class Message(
//    @SerializedName("msgId")
    val msgId: Int,
    @SerializedName("userIdFk")
    val userIdFk: Int,
   @SerializedName("text")
    val msg: String,
//    @SerializedName("date")
    val date: String
)