package com.androidschool.bridgesnetworkapplication.domain

import com.androidschool.bridgesnetworkapplication.data.network.BridgesApiService
import com.androidschool.bridgesnetworkapplication.domain.mapper.BridgeInfoMapper
import com.androidschool.bridgesnetworkapplication.domain.mapper.SimpleBridgeMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NetworkInteractor constructor(
    private val bridgesApiService: BridgesApiService,
    private val bridgeId: Int? = null
) {

    fun getBridges() = bridgesApiService.getBridges()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            return@map it.map {
                SimpleBridgeMapper.mapApiToDomain(it)
            }.toList()
        }

    fun getBridgesInfo() = bridgeId?.let {
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