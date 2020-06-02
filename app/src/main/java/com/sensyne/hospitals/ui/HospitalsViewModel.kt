package com.sensyne.hospitals.ui

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sensyne.hospitals.model.Hospitals

class HospitalsViewModel(private val hospitalsInteractor: HospitalsInteractor) : ViewModel() {

    val _hospitalsData = MutableLiveData<Hospitals>()
    val hospitalsData: LiveData<Hospitals> = _hospitalsData

    val dataReady = ObservableBoolean(false)

    fun getHospitalInformation() {
        dataReady.set(false)
        hospitalsInteractor.getHospitalInformation({
            _hospitalsData.value = it
            dataReady.set(true)
        }, { networkError ->
            Log.d("HospitalsViewModel", "Network error = $networkError")
        })
    }
}
