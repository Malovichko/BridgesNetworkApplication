package com.androidschool.bridgesnetworkapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class DivorcesBridgeApiModel(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String
)