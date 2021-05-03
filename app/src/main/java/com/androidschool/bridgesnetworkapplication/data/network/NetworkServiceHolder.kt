package com.androidschool.bridgesnetworkapplication.data.network

import retrofit2.Retrofit

object NetworkServiceHolder {
    var retrofit: Retrofit? = null
        set(value) {
            retrofitService = value!!.create(BridgesApiService::class.java)
            field = value
        }

    var retrofitService: BridgesApiService? = null
}