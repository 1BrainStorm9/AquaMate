package com.example.aquamate.ui.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class WaterVolumeState(
    val value: Float = 0f
)

class AddWaterViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_WATER_VOLUMES = "water_volumes"
    }

    private val _uiState = MutableStateFlow(
        savedStateHandle.get<List<WaterVolumeState>>(KEY_WATER_VOLUMES) ?: emptyList()
    )
    val uiState: StateFlow<List<WaterVolumeState>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.collect { updatedList ->
                savedStateHandle[KEY_WATER_VOLUMES] = updatedList
            }
        }
    }

    fun addToList(item: WaterVolumeState) {
        _uiState.update { currentList -> currentList + item }
    }
}
