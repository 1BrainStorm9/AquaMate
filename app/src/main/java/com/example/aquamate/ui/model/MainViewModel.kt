package com.example.aquamate.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class WaterTrackerState(
    val currentValue: Float = 0f,
    val totalValue: Float = 2000f
)

class MainViewModel : ViewModel() {
    private val internalState  = MutableStateFlow(WaterTrackerState())

    private val state: WaterTrackerState
        get() = internalState.value

    val uiState = internalState.asStateFlow()

    fun addProgress(amount: Float) {
        val newAmount = state.currentValue + amount
        internalState.value = state.copy(currentValue = newAmount)
        Log.d("!!!",internalState.value.toString())
    }
}
