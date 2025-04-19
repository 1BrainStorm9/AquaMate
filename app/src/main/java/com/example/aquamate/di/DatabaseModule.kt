package com.example.aquamate.di

import AppDatabase
import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "aquamate-db"
        ).build()
    }

    single { get<AppDatabase>().waterVolumeDao() }
    single { get<AppDatabase>().waterTrackerDao() }
}