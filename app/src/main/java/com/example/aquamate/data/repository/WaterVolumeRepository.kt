package com.example.aquamate.data.repository

import WaterVolume
import WaterVolumeDao
import kotlinx.coroutines.flow.Flow

class WaterVolumeRepository(private val dao: WaterVolumeDao) {
    val allVolumes: Flow<List<WaterVolume>> = dao.getAll()

    suspend fun addVolume(item: WaterVolume) = dao.insert(item)
}
