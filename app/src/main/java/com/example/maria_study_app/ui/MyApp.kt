package com.example.maria_study_app.ui

import android.app.Application
import com.example.maria_study_app.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            modules(module)
            androidContext(this@MyApp)
        }
    }
}