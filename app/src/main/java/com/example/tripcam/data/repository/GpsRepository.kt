package com.example.tripcam.data.repository

import android.content.Context
import android.net.Uri
import com.example.tripcam.data.model.RutaGPS
import com.example.tripcam.data.network.RetrofitClient
import com.example.tripcam.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class GpsRepository {

    // ----------------------
    // C - CREATE (POST)
    // ----------------------
    suspend fun createRuta(context: Context, nombre: String, inicioTimestamp: Long, imageUri: Uri?): RutaGPS {
        // 1. Convertir campos de texto a RequestBody
        val nombreBody = nombre.toRequestBody("text/plain".toMediaType())
        val inicioTimestampBody = inicioTimestamp.toString().toRequestBody("text/plain".toMediaType())

        var imagePart: MultipartBody.Part? = null

        // 2. Manejar la imagen (Multipart)
        imageUri?.let {
            val file = uriToFile(context, it) // Convertir Uri a File [cite: 67]
            val requestFile = file.asRequestBody("image/*".toMediaType())

            // Crear la parte Multipart
            imagePart = MultipartBody.Part.createFormData(
                "image", // Nombre de la clave que espera el servidor de Flask
                file.name,
                requestFile
            )
        }

        // 3. Llamar al servicio de Retrofit
        return RetrofitClient.api.createRuta(
            nombreBody,
            inicioTimestampBody,
            imagePart
        )
    }

    // ----------------------
    // R - READ (GET ALL)
    // ----------------------
    suspend fun getAllRutas(): List<RutaGPS> {
        return RetrofitClient.api.getRutas()
    }

    // ----------------------
    // U - UPDATE (PUT)
    // ----------------------
    suspend fun updateRuta(context: Context, id: Int, nombre: String, finTimestamp: Long, imageUri: Uri?): RutaGPS {
        // 1. Convertir campos de texto a RequestBody
        val nombreBody = nombre.toRequestBody("text/plain".toMediaType())
        val finTimestampBody = finTimestamp.toString().toRequestBody("text/plain".toMediaType())

        var imagePart: MultipartBody.Part? = null

        // 2. Manejar la imagen (Multipart)
        imageUri?.let {
            val file = uriToFile(context, it) // Convertir Uri a File [cite: 74]
            val requestFile = file.asRequestBody("image/*".toMediaType())

            // Crear la parte Multipart
            imagePart = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestFile
            )
        }

        // 3. Llamar al servicio de Retrofit
        return RetrofitClient.api.updateRuta(
            id,
            nombreBody,
            finTimestampBody,
            imagePart
        )
    }

    // ----------------------
    // D - DELETE (DELETE)
    // ----------------------
    suspend fun deleteRuta(id: Int) {
        RetrofitClient.api.deleteRuta(id)
    }
}