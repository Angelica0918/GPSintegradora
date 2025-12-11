package com.example.tripcam.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

fun uriToFile(context: Context, uri: Uri): File {
    // Abre el flujo de entrada de la URI
    val inputStream = context.contentResolver.openInputStream(uri)

    // Crea un archivo temporal en el directorio cache (ej. upload_1234.jpg)
    val file = File.createTempFile("upload_", ".jpg", context.cacheDir)

    // Crea un flujo de salida para escribir en el archivo temporal [cite: 62]
    val output = FileOutputStream(file)

    // Copia el contenido del flujo de entrada al flujo de salida [cite: 62]
    inputStream?.copyTo(output)

    // Cierra los flujos
    output.close()
    inputStream?.close()

    return file
}