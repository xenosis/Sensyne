package com.sensyne.hospitals.di

import com.sensyne.hospitals.ui.HospitalsInteractor
import com.sensyne.hospitals.ui.HospitalsViewModel
import org.koin.dsl.module

val hospitalsModule = module {
    factory { HospitalsViewModel(get()) }
    factory { HospitalsInteractor(get()) }
}