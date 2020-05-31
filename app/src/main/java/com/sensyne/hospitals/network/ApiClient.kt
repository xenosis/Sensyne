package com.sensyne.hospitals.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sensyne.hospitals.model.Hospitals
import com.sensyne.hospitals.utils.CSVParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader

object ApiClient {

    private const val HOSPITALS_ENDPOINT: String = "http://media.nhschoices.nhs.uk/"

    private val retrofit: Retrofit

    init {
        val httpClient = OkHttpClient().newBuilder()

        retrofit = Retrofit.Builder().baseUrl(HOSPITALS_ENDPOINT)
            .client(httpClient.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getHospitalInformation(success: (Hospitals) -> Unit, networkError: (Boolean) -> Unit) {
        val downloadApi: DownloadApi = retrofit.create(DownloadApi::class.java)

        downloadApi.getHospitalInformation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribeBy(
                onNext = { response ->
                    when (response.code()) {
                        200 -> {
                            val reader = BufferedReader(InputStreamReader(response.body()?.byteInputStream(), "UTF-8"))
                            val hospitals = CSVParser.parse(reader)

                            success(hospitals)
                        }
                        else -> {
                            networkError(false)
                        }
                    }
                },
                onError = {
                    networkError(true)
                }
            )
    }
}
