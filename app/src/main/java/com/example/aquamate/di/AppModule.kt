package com.example.aquamate.di

import com.example.aquamate.data.repository.WaterTrackerRepository
import com.example.aquamate.data.repository.WaterVolumeRepository
import com.example.aquamate.ui.model.AddWaterViewModel
import com.example.aquamate.ui.model.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::WaterTrackerRepository)
    singleOf(::WaterVolumeRepository)

    viewModelOf(::MainViewModel)
    viewModelOf(::AddWaterViewModel)
}