package com.androidschool.bridgesnetworkapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class BridgesPropertiesApiModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photo_open_url")
    val photo_open_url: String,
    @SerializedName("photo_close_url")
    val photo_close_url: String
)