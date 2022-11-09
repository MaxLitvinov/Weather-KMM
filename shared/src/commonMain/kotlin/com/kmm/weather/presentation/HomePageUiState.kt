package com.kmm.weather.presentation

sealed class HomePageUiState {

    object Loading : HomePageUiState()

    data class Success(val weatherModel: WeatherModel) : HomePageUiState()

    data class Failure(val message: String) : HomePageUiState()
}