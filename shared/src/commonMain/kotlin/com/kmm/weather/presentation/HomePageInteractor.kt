package com.kmm.weather.presentation

import com.kmm.weather.domain.IpDomainResult
import com.kmm.weather.domain.IpRepository
import com.kmm.weather.domain.WeatherDomainModel
import com.kmm.weather.domain.WeatherDomainResult
import com.kmm.weather.domain.WeatherForecastRepository

class HomePageInteractor constructor(
    private val ipRepository: IpRepository,
    private val weatherForecastRepository: WeatherForecastRepository,
    private val domainMapper: WeatherDomainModelMapper,
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
            is WeatherDomainResult.Success -> HomePageUiState.Success(
                transformDomainModel(ipDomainResult.city, weatherDomainResult.model)
            )
            is WeatherDomainResult.Failure -> failure(weatherDomainResult.error)
        }
    }

    private fun transformDomainModel(
        city: String,
        domainModel: WeatherDomainModel
    ): WeatherModel = domainMapper.mapToPresentationModel(city, domainModel)
}
