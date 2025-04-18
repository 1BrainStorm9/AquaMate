package com.example.aquamate.di

import androidx.lifecycle.SavedStateHandle
import com.example.aquamate.ui.model.AddWaterViewModel
import com.example.aquamate.ui.model.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
    viewModel { (handle: SavedStateHandle) ->
        AddWaterViewModel(handle)
    }
}