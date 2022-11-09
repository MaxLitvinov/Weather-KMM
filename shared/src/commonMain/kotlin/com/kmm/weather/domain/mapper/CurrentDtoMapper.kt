package com.kmm.weather.domain.mapper

import com.kmm.weather.data.CurrentDto
import com.kmm.weather.domain.CurrentWeatherDomainModel

class CurrentDtoMapper constructor(
    private val weatherDetailsDtoMapper: WeatherDetailsDtoMapper
) {

    fun mapToDomainModel(dto: CurrentDto) = with(dto) {
        CurrentWeatherDomainModel(
            currentTime = currentTime,
            sunriseTime = sunriseTime,
            sunsetTime = sunsetTime,
            temperature = temperature,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            clouds = clouds,
            uvIndex = uvIndex,
            visibility = visibility,
            windDeg = windDeg,
            windGust = windGust,
            windSpeed = windSpeed,
            lastHourRainVolume = rain?.lastHourVolume,
            lastHourSnowVolume = snow?.lastHourVolume,
            weatherDetails = weatherDetailsDtoMapper.mapToDomainModel(weather.first())
        )
    }
}
