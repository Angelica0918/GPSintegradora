package com.example.tripcam.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GpsViewModel(private val repository: GpsRepository = GpsRepository()) : ViewModel() {

    private val _rutas = MutableStateFlow<List<RutaGPS>>(emptyList())
    val rutas: StateFlow<List<RutaGPS>> = _rutas

    init {
        cargarRutas()
    }

    fun cargarRutas() {
        viewModelScope.launch {
            try {
                val rutasApi = repository.getAllRutas()
                _rutas.value = rutasApi // Actualizar el estado [cite: 65]
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejo de errores
            }
        }
    }

    fun insertarRuta(context: Context, nombre: String, inicioTimestamp: Long, imageUri: Uri?) {
        viewModelScope.launch {
            try {
                repository.createRuta(context, nombre, inicioTimestamp, imageUri)
                cargarRutas() // Recargar la lista después de la inserción [cite: 70]
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejo de errores
            }
        }
    }

    fun actualizarRuta(context: Context, id: Int, nombre: String, finTimestamp: Long, imageUri: Uri?) {
        viewModelScope.launch {
            try {
                repository.updateRuta(context, id, nombre, finTimestamp, imageUri)
                cargarRutas() // Recargar la lista después de la actualización
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun borrarRuta(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteRuta(id)
                cargarRutas() // Recargar la lista [cite: 71]
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}