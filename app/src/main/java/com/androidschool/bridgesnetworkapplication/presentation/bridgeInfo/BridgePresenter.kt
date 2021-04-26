package com.androidschool.bridgesnetworkapplication.presentation.bridgeInfo

import com.androidschool.bridgesnetworkapplication.data.network.NetworkServiceHolder
import com.androidschool.bridgesnetworkapplication.domain.NetworkInteractor
import com.androidschool.bridgesnetworkapplication.domain.mapper.BridgeInfoMapper
import com.androidschool.bridgesnetworkapplication.domain.mapper.SimpleBridgeMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BridgePresenter constructor(private val view: BridgeView) {
    private val retrofitService = NetworkServiceHolder.retrofitService!!

    fun onViewCreated(bridgeId: Int?) {
        val interactor = NetworkInteractor(retrofitService)

        bridgeId?.let {
            val interactor = NetworkInteractor(retrofitService, it)
            interactor.getBridgesInfo()
                ?.subscribe(
                    {
                        view.fillData(
                            it.id,
                            it.name,
                            it.description,
                            it.photo_open_url,
                            it.photo_close_url
                        )
                    },
                    {
                        it.printStackTrace()
                    }
                )
        }

        interactor.getTimes()
            .subscribe(
                {
                    if (bridgeId != null) {
                        view.setupTimeList(it, bridgeId)
                    }
                },
                {
                    it.printStackTrace()
                }
            )
    }
}