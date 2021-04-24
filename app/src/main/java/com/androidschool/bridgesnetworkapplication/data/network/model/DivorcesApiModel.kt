package com.androidschool.bridgesnetworkapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class DivorcesApiModel(
    @SerializedName("divorces")
    val divorces: List<DivorcesBridgeApiModel>
)