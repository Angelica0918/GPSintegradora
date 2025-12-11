package com.example.tripcam.data.model

data class RutaGPS(
    val id: Int = 0,
    val nombre: String,
    val inicioTimestamp: Long,
    val finTimestamp: Long? = null,
    // Este campo guardará la URL remota de la imagen
    val fotoUrl: String? = null
)
