package com.example.app_demo_chat.model

import com.google.gson.annotations.SerializedName

data class MessageBody(
    val msgId: Int,
    val userId: Int,
    @SerializedName("text")
    val msg: String,
    val date: String
)
