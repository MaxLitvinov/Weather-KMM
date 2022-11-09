package com.kmm.weather.presentation

import com.kmm.weather.domain.DailyDomainModel
import com.kmm.weather.domain.WeatherDomainModel

class WeatherDomainModelMapper {

    fun mapToPresentationModel(city: String, domainModel: WeatherDomainModel): WeatherModel =
        WeatherModel(
            city = city,
            iconUrl = domainModel.currentWeather.weatherDetails.iconUrl,
            temperature = "${domainModel.currentWeather.temperature}",
            weatherDescription = domainModel.currentWeather.weatherDetails.detailedDescription,
            dailyForecasts = domainModel.dailyForecasts.map(::getDailyForecast)
        )

    private fun getDailyForecast(domainModel: DailyDomainModel): DayForecast = with(domainModel) {
        DayForecast(
            dayName = time.toString(),
            morning = temperature.morning,
            day = temperature.day,
            evening = temperature.evening,
            night = temperature.night,
            min = temperature.min,
            max = temperature.max
        )
    }
}
