package com.example.tripcam.data.network

import com.example.tripcam.data.model.RutaGPS
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface GpsApiService {
    @GET("rutas")
    suspend fun getRutas(): List<RutaGPS>

    @Multipart
    @POST("rutas")
    suspend fun createRuta(
        @Part("nombre") nombre: RequestBody,
        @Part("inicioTimestamp") inicioTimestamp: RequestBody,
        @Part image: MultipartBody.Part?
    ): RutaGPS

    @Multipart
    @PUT("rutas/{id}")
    suspend fun updateRuta(
        @Path("id") id: Int,
        @Part("nombre") nombre: RequestBody,
        @Part("finTimestamp") finTimestamp: RequestBody,
        @Part image: MultipartBody.Part?
    ): RutaGPS

    @DELETE("rutas/{id}")
    suspend fun deleteRuta(@Path("id") id: Int)
}