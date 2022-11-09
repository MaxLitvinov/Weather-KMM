package com.kmm.weather.domain.mapper

import com.kmm.weather.data.DailyDto
import com.kmm.weather.domain.DailyDomainModel

class DailyDtoMapper constructor(
    private val temperatureDtoMapper: TemperatureDtoMapper,
    private val feelsLikeDtoMapper: FeelsLikeDtoMapper
) {

    fun mapToDomainModel(timezoneOffset: Int, dto: DailyDto) = with(dto) {
        DailyDomainModel(
            time = time + timezoneOffset,
            sunriseTime = sunriseTime,
            sunsetTime = sunsetTime,
            moonriseTime = moonriseTime,
            moonsetTime = moonsetTime,
            moonPhase = moonPhase,
            temperature = temperatureDtoMapper.mapToDomainModel(temperature),
            feelsLike = feelsLikeDtoMapper.mapToDomainModel(feelsLike),
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            clouds = clouds,
            uvIndex = uvIndex,
            pop = pop,
            rain = rain,
            snow = snow
        )
    }
}
