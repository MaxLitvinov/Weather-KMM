package com.kmm.weather.domain.mapper

import com.kmm.weather.data.HourlyDto
import com.kmm.weather.domain.HourlyDomainModel

class HourlyDtoMapper constructor(
    private val weatherMapper: WeatherDetailsDtoMapper
) {

    fun mapToDomainModel(
        timezoneOffset: Int,
        dto: HourlyDto
    ) = with(dto) {
        HourlyDomainModel(
            time = time + timezoneOffset,
            temperature = temperature,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            uvIndex = uvIndex,
            clouds = clouds,
            visibility = visibility,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            weather = weatherMapper.mapToDomainModel(weather.first()),
            pop = pop
        )
    }
}
