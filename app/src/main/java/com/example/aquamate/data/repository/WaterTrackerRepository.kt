package com.example.aquamate.data.repository

import WaterTracker
import WaterTrackerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class WaterTrackerRepository(private val dao: WaterTrackerDao) {


    fun getTrackerByDate(date: String): Flow<WaterTracker?> {
        return dao.getByDate(date)
    }


    suspend fun addProgressByDate(date: String,amount: Float, totalValue: Float = 2000f) {
        val current = dao.getByDate(date).firstOrNull()
        val updated = current?.copy(currentValue = current.currentValue + amount)
            ?: WaterTracker(date = date, currentValue = amount, totalValue = totalValue)
        dao.insertOrUpdate(updated)
    }
}
