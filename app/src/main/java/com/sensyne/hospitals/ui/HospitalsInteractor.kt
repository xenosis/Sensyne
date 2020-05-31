package com.sensyne.hospitals.ui

import com.sensyne.hospitals.model.Hospitals
import com.sensyne.hospitals.network.ApiClient

class HospitalsInteractor(private val apiClient: ApiClient) {

    fun getHospitalInformation(success: (Hospitals) -> Unit, error: (Boolean) -> Unit) {
        apiClient.getHospitalInformation({ hospitals -> success(hospitals) }, { networkError -> error(networkError) }) }
    }