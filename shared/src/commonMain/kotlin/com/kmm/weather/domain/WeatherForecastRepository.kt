package com.kmm.weather.domain

import com.kmm.weather.data.NetworkClient
import com.kmm.weather.data.WeatherDto
import com.kmm.weather.domain.mapper.WeatherDtoMapper
import io.ktor.client.call.*
import io.ktor.client.request.*

class WeatherForecastRepository constructor(
    private val networkClient: NetworkClient,
    private val mapper: WeatherDtoMapper
) {

    suspend fun fetchWeather(lat: String, lon: String): WeatherDomainResult {
        val apiKey = "978539e18a215484b0146ed80b932145"
        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lon&lang=en&units=metric&appid=$apiKey"
        val request = networkClient.getClient().get(url)
        return try {
            val weatherDto = request.body<WeatherDto>()
            val weatherDomainModel = mapper.mapToDomainModel(weatherDto)
            WeatherDomainResult.Success(weatherDomainModel)
        } catch (e: Exception) {
            e.printStackTrace()
            WeatherDomainResult.Failure("Error message from WeatherForecastRepository: ${e.message ?: "Unknown error"}")
        }
    }
}
