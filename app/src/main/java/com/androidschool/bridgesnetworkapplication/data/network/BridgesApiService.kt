package com.androidschool.bridgesnetworkapplication.data.network

import com.androidschool.bridgesnetworkapplication.data.network.model.BridgesPropertiesApiModel
import com.androidschool.bridgesnetworkapplication.data.network.model.DivorcesApiModel
import com.androidschool.bridgesnetworkapplication.data.network.model.SimpleBridgeApiModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BridgesApiService {
    @GET("bridges")
    fun getBridges(): Single<List<SimpleBridgeApiModel>>

    @GET("bridges")
    fun getTimes(): Single<List<DivorcesApiModel>>

    @GET("bridges/{bridgeId}")
    fun getBridgeInfo(@Path("bridgeId") id: Int): Single<BridgesPropertiesApiModel>


}