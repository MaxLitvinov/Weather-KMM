package com.kmm.weather.domain.mapper

import com.kmm.weather.data.WeatherDetailsDto
import com.kmm.weather.domain.WeatherDetailsDomainModel

class WeatherDetailsDtoMapper {

    fun mapToDomainModel(dto: WeatherDetailsDto) = with(dto) {
        WeatherDetailsDomainModel(
            id = id,
            mainDescription = mainDescription,
            detailedDescription = description,
            iconUrl = getIconUrl(icon)
        )
    }

    private fun getIconUrl(iconName: String): String =
        "https://openweathermap.org/img/wn/$iconName@2x.png"
}
