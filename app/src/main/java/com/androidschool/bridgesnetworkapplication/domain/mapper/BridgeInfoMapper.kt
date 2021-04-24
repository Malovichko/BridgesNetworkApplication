package com.androidschool.bridgesnetworkapplication.domain.mapper

import com.androidschool.bridgesnetworkapplication.data.network.model.BridgesPropertiesApiModel
import com.androidschool.bridgesnetworkapplication.data.network.model.DivorcesApiModel
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeInfoModel
import com.androidschool.bridgesnetworkapplication.domain.model.TimeModel

class BridgeInfoMapper {
    companion object {

        fun mapApiToDomain(source: BridgesPropertiesApiModel) = BridgeInfoModel(
            source.id,
            source.name,
            source.description,
            source.photo_open_url,
            source.photo_close_url
        )
    }
}