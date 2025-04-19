package com.example.aquamate.ui.model

import WaterTracker
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquamate.data.repository.WaterTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel(
    private val repository: WaterTrackerRepository
) : ViewModel() {

    private var currentDate: LocalDate = LocalDate.now()
    private val _uiState = MutableStateFlow<WaterTracker>(WaterTracker())
    val uiState: StateFlow<WaterTracker> = _uiState

    init {
        loadTrackerForCurrentDate()
    }

    fun addProgress(amount: Float) {
        viewModelScope.launch {
            repository.addProgressByDate(currentDate.toString(), amount)
            loadTrackerForCurrentDate()
        }
    }

    fun setDate(date: LocalDate) {
        currentDate = date
        loadTrackerForCurrentDate()
    }

    fun moveToPreviousDay() {
        currentDate = currentDate.minusDays(1)
        loadTrackerForCurrentDate()
    }

    fun moveToNextDay() {
        currentDate = currentDate.plusDays(1)
        loadTrackerForCurrentDate()
    }

    private fun loadTrackerForCurrentDate() {
        viewModelScope.launch {
            repository.getTrackerByDate(currentDate.toString())
                .collect { tracker ->
                    _uiState.value = tracker ?: WaterTracker(date = currentDate.toString())
                }
        }
    }

    fun getCurrentDate(): LocalDate = currentDate
}
