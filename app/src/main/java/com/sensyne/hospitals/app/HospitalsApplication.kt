package com.sensyne.hospitals.app

import android.app.Application
import com.sensyne.hospitals.di.appModule
import com.sensyne.hospitals.di.hospitalsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HospitalsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@HospitalsApplication)
            modules(listOf(appModule, hospitalsModule))
        }
    }
}


