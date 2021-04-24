package com.androidschool.bridgesnetworkapplication.presentation.bridgesList

import com.androidschool.bridgesnetworkapplication.data.network.NetworkServiceHolder
import com.androidschool.bridgesnetworkapplication.domain.mapper.SimpleBridgeMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BridgesListPresenter constructor(private val view: BridgesListView) {

    private val retrofitService = NetworkServiceHolder.retrofitService!!

    fun onViewCreated() {
        retrofitService.getBridges()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                return@map it.map {
                    SimpleBridgeMapper.mapApiToDomain(it)
                }.toList()
            }
            .subscribe(
                {
                    view.setupItemList(it)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}