package com.androidschool.bridgesnetworkapplication.presentation.mapScreen

import com.androidschool.bridgesnetworkapplication.data.network.NetworkServiceHolder
import com.androidschool.bridgesnetworkapplication.domain.NetworkInteractor


class MapPresenter constructor(private val view: MapView) {
    private val retrofitService = NetworkServiceHolder.retrofitService!!

    fun onViewCreated() {
        val interactor = NetworkInteractor(retrofitService)

        interactor.getBridges()
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