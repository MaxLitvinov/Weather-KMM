package com.kmm.weather.domain.mapper

import com.kmm.weather.data.TemperatureDto
import com.kmm.weather.domain.TemperatureDomainModel

class TemperatureDtoMapper constructor() {

    fun mapToDomainModel(dto: TemperatureDto) = with(dto) {
        TemperatureDomainModel(
            morning = morning,
            day = day,
            evening = evening,
            night = night,
            min = min,
            max = max
        )
    }
}
