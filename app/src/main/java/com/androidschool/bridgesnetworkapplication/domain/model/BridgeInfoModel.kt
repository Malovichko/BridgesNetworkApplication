package com.androidschool.bridgesnetworkapplication.domain.model


data class BridgeInfoModel(
    val id: Int,
    val name: String,
    val description: String,
    val photo_open_url: String,
    val photo_close_url: String,
)