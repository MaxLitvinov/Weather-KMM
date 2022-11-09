package com.kmm.weather.domain.mapper

import com.kmm.weather.data.FeelsLikeDto
import com.kmm.weather.domain.FeelsLikeDomainModel

class FeelsLikeDtoMapper constructor() {

    fun mapToDomainModel(dto: FeelsLikeDto) = with(dto) {
        FeelsLikeDomainModel(
            day = day,
            night = night,
            evening = evening,
            morning = morning
        )
    }
}
