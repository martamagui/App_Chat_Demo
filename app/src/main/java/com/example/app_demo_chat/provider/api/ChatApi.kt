package com.example.app_demo_chat.provider.api

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient


object ChatApi {
    //    private val logginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logginInterceptor).build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://192.168.56.1:8080/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(ChatService::class.java)
}