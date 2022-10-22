package com.kmm.weather.home_page

import com.kmm.weather.home_page.ip_api.IpDomainResult
import com.kmm.weather.home_page.ip_api.IpRepository
import com.kmm.weather.home_page.open_weather_map.WeatherDomainResult
import com.kmm.weather.home_page.open_weather_map.WeatherForecastRepository

class HomePageInteractor constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val ipRepository: IpRepository,
    //private val weatherDataStoreRepository: WeatherDataStoreRepository,
    //private val mapper: WeatherDomainModelMapper
) {

    suspend fun fetchWeather(): HomePageUiState = handleLocation()

    private suspend fun handleLocation(): HomePageUiState =
        when (val ipDomainResult = ipRepository.fetchLocation()) {
            is IpDomainResult.Success -> handleWeather(ipDomainResult)
            is IpDomainResult.Failure -> failure(ipDomainResult.error)
        }

    private fun failure(errorMessage: String?) = HomePageUiState.Failure(
        errorMessage ?: "Unknown message"
    )

    private suspend fun handleWeather(
        ipDomainResult: IpDomainResult.Success
    ): HomePageUiState = with(ipDomainResult) {
        when (val weatherDomainResult = weatherForecastRepository.fetchWeather("$lat", "$lon")) {
            is WeatherDomainResult.Success -> {
                val weatherDomainModel = weatherDomainResult.model

                val weatherModel = WeatherModel(
                    city = ipDomainResult.city,
                    iconUrl = "",
                    temperature = "${weatherDomainModel.currentWeather.temperature} °C",
                    weatherDescription = weatherDomainModel.currentWeather.weatherDetails.detailedDescription,
                    dailyForecasts = listOf()
                )

                HomePageUiState.Success(weatherModel)
            }
            is WeatherDomainResult.Failure -> failure(weatherDomainResult.error)
        }
    }
}
