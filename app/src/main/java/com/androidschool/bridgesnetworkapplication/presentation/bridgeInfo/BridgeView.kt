package com.androidschool.bridgesnetworkapplication.presentation.bridgeInfo

import com.androidschool.bridgesnetworkapplication.domain.model.TimeModel

interface BridgeView {
    fun fillData(
        id: Int,
        name: String,
        description: String,
        photo_open_url: String,
        photo_close_url: String
    )

    fun setupTimeList(list: List<TimeModel>, id: Int)
}