package com.kmm.weather.di

import com.kmm.weather.home_page.HomePageInteractor
import com.kmm.weather.home_page.open_weather_map.WeatherForecastRepository
import com.kmm.weather.home_page.ip_api.IpRepository
import com.kmm.weather.network.NetworkClient
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val KodeinInjector = DI {
    bindSingleton { NetworkClient() }
    bindSingleton { WeatherForecastRepository(this.di) }
    bindSingleton { IpRepository(this.di) }
    bindSingleton { HomePageInteractor(instance(), instance()) }
}