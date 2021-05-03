package com.androidschool.bridgesnetworkapplication.presentation.mapScreen

import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel

interface MapView {
    fun setupItemList(list: List<BridgeModel>)
}