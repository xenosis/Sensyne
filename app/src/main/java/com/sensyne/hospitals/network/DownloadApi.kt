package com.sensyne.hospitals.network

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Streaming

interface DownloadApi {

    @Streaming
    @Headers("Content-type: text/csv", "Accept: text/csv")
    @GET("data/foi/Hospital.csv")
    fun getHospitalInformation() : Observable<Response<String>>
}