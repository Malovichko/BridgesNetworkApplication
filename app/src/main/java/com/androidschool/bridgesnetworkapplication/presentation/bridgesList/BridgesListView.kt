package com.androidschool.bridgesnetworkapplication.presentation.bridgesList

import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel

interface BridgesListView {

    fun setupItemList(list: List<BridgeModel>)
}