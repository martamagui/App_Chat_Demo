package com.example.app_demo_chat.provider


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.example.app_demo_chat.model.Message
import com.example.app_demo_chat.model.MessageBody

interface ChatService {
    @GET("msg")
    fun getMessage(): Call<List<Message>>

    @POST("msg")
    fun addMessage(@Body msg: Message):Call<Any>
}