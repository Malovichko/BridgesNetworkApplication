package com.androidschool.bridgesnetworkapplication

import android.app.Application
import com.androidschool.bridgesnetworkapplication.data.network.NetworkServiceHolder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BridgesNetworkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initRetrofitService()
    }

    private fun initRetrofitService() {
        NetworkServiceHolder.retrofit = Retrofit.Builder()
            .baseUrl("http://gdemost.handh.ru:1235/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

}