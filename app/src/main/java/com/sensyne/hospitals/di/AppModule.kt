package com.sensyne.hospitals.di

import com.sensyne.hospitals.network.ApiClient
import org.koin.dsl.module

val appModule = module {
    factory { ApiClient }
}