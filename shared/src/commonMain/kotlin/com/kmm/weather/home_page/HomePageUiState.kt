package com.kmm.weather.home_page

sealed class HomePageUiState {

    object Loading : HomePageUiState()

    data class Success(val weatherModel: WeatherModel) : HomePageUiState()

    data class Failure(val message: String) : HomePageUiState()
}