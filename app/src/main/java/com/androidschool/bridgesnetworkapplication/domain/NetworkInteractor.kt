package com.androidschool.bridgesnetworkapplication.domain

import com.androidschool.bridgesnetworkapplication.data.network.BridgesApiService
import com.androidschool.bridgesnetworkapplication.domain.mapper.BridgeInfoMapper
import com.androidschool.bridgesnetworkapplication.domain.mapper.SimpleBridgeMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NetworkInteractor constructor(
    private val bridgesApiService: BridgesApiService,
) {

    private var id: Int? = 0

    constructor(bridgesApiService: BridgesApiService, bridgeId: Int?) : this(bridgesApiService) {
        id = bridgeId
    }

    fun getBridges() = bridgesApiService.getBridges()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            return@map it.map {
                SimpleBridgeMapper.mapApiToDomain(it)
            }.toList()
        }

    fun getBridgesInfo() = id?.let {
        bridgesApiService.getBridgeInfo(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                BridgeInfoMapper.mapApiToDomain(it)
            }
    }

    fun getTimes() = bridgesApiService.getTimes().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            return@map it.map {
                SimpleBridgeMapper.mapApiTimeToDomain(it)
            }.toList()
        }
}