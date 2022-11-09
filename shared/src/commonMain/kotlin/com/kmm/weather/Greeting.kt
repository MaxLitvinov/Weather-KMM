package com.kmm.weather

import com.kmm.weather.presentation.HomePageInteractor
import com.kmm.weather.presentation.HomePageUiState

class Greeting {
    private val platform: Platform = getPlatform()

    private val interactor: HomePageInteractor = InteractorProvider.homePageInteractor

    suspend fun getWeatherData(): HomePageUiState = interactor.fetchWeather()

    fun greeting(): String = "Hello, ${platform.name}!"
}