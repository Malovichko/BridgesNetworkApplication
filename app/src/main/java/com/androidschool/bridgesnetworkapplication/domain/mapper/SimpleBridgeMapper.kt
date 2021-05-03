package com.androidschool.bridgesnetworkapplication.domain.mapper

import com.androidschool.bridgesnetworkapplication.data.network.model.DivorcesApiModel
import com.androidschool.bridgesnetworkapplication.data.network.model.SimpleBridgeApiModel
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel
import com.androidschool.bridgesnetworkapplication.domain.model.TimeModel

object SimpleBridgeMapper {

    fun mapApiToDomain(source: SimpleBridgeApiModel) = BridgeModel(
        source.id,
        source.name,
        source.divorces[0].start,
        source.divorces[0].end,
        source.lat,
        source.lng
    )

    fun mapApiTimeToDomain(source: DivorcesApiModel) = TimeModel(
        source.divorces[0].start,
        source.divorces[0].end
    )
}