package com.example.aquamate.ui.model

import WaterVolume
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquamate.data.repository.WaterVolumeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class AddWaterViewModel(private val repository: WaterVolumeRepository) : ViewModel() {
    val uiState: StateFlow<List<WaterVolume>> = repository.allVolumes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addVolume(value: Float) {
        viewModelScope.launch {
            repository.addVolume(WaterVolume(value = value))
        }
    }
}

