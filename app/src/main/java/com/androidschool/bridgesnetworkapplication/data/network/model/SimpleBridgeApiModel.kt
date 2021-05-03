package com.androidschool.bridgesnetworkapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class SimpleBridgeApiModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("divorces")
    val divorces: List<DivorcesBridgeApiModel>,
    @SerializedName( "lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)