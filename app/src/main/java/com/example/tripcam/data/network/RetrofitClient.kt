package com.example.tripcam.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.XXX:8000/" // <-- ¡ACTUALIZA TU IP AQUÍ!

    val api: GpsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GpsApiService::class.java)
    }
}