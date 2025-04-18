package com.example.aquamate

import android.app.Application
import com.example.aquamate.di.appModule
import com.example.aquamate.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(databaseModule,appModule)
        }
    }
}