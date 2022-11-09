package com.kmm.weather.domain.mapper

import com.kmm.weather.data.DailyDto
import com.kmm.weather.data.HourlyDto
import com.kmm.weather.data.WeatherDto
import com.kmm.weather.domain.DailyDomainModel
import com.kmm.weather.domain.HourlyDomainModel
import com.kmm.weather.domain.WeatherDomainModel

class WeatherDtoMapper constructor(
    private val currentDtoMapper: CurrentDtoMapper,
    private val hourlyDtoMapper: HourlyDtoMapper,
    private val dailyDtoMapper: DailyDtoMapper
) {

    fun mapToDomainModel(
        dto: WeatherDto
    ) = with(dto) {
        WeatherDomainModel(
            latitude = latitude,
            longitude = longitude,
            timezone = timezone,
            timezoneOffset = timezoneOffset,
            currentWeather = currentDtoMapper.mapToDomainModel(current),
            dailyForecasts = getDailyForecasts(timezoneOffset, dailyForecasts, hourlyForecasts)
        )
    }

    // TODO: Fix getDailyForecasts
    private fun getDailyForecasts(
        timezoneOffset: Int,
        dailyForecastDtoList: List<DailyDto>,
        hourlyForecastDtoList: List<HourlyDto>?
    ): List<DailyDomainModel> {
        val hourlyMap = getHourlyMap(timezoneOffset, hourlyForecastDtoList)

        return dailyForecastDtoList.map { dailyDto ->
            val dailyForecastDomain = dailyDtoMapper.mapToDomainModel(timezoneOffset, dailyDto)
            val time = transformDate(dailyForecastDomain.time)
            if (hourlyMap?.containsKey(time) == true) {
                dailyForecastDomain.copy(hourlyForecasts = hourlyMap[time])
            } else {
                dailyForecastDomain
            }
        }
    }

    private fun getHourlyMap(
        timezoneOffset: Int,
        hourlyForecastDtoList: List<HourlyDto>?
    ): Map<String, MutableList<HourlyDomainModel>>? {
        if (hourlyForecastDtoList.isNullOrEmpty()) {
            return null
        }
        val hourlyMap = HashMap<String, MutableList<HourlyDomainModel>>()

        hourlyForecastDtoList.map { hourlyDto ->
            val hourlyForecastDomain = hourlyDtoMapper.mapToDomainModel(timezoneOffset, hourlyDto)

            val date = transformDate(hourlyForecastDomain.time)
            when (hourlyMap.containsKey(date)) {
                true -> hourlyMap[date]?.add(hourlyForecastDomain)
                false -> hourlyMap[date] = mutableListOf(hourlyForecastDomain)
            }

            hourlyForecastDomain
        }
        return hourlyMap
    }

    private fun transformDate(dateTime: Long) = "$dateTime"// timestampProvider.toYearMonthDay(dateTime)
}
