package com.example.tripcam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripcam.data.db.Trip
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow


class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TripRepository(application)
    val completedTrips: StateFlow<List<Trip>> =
        repository.getAllCompletedTrips()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

}