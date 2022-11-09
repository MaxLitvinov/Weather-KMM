package com.kmm.weather.di

import com.kmm.weather.data.NetworkClient
import com.kmm.weather.domain.IpRepository
import com.kmm.weather.domain.WeatherForecastRepository
import com.kmm.weather.domain.mapper.CurrentDtoMapper
import com.kmm.weather.domain.mapper.DailyDtoMapper
import com.kmm.weather.domain.mapper.FeelsLikeDtoMapper
import com.kmm.weather.domain.mapper.HourlyDtoMapper
import com.kmm.weather.domain.mapper.IpDtoMapper
import com.kmm.weather.domain.mapper.TemperatureDtoMapper
import com.kmm.weather.domain.mapper.WeatherDetailsDtoMapper
import com.kmm.weather.domain.mapper.WeatherDtoMapper
import com.kmm.weather.presentation.HomePageInteractor
import com.kmm.weather.presentation.WeatherDomainModelMapper
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val KodeinInjector = DI {
    bindSingleton { NetworkClient() }
    bindSingleton { WeatherDomainModelMapper() }

    bindSingleton { WeatherForecastRepository(instance(), instance()) }

    bindSingleton { IpDtoMapper() }
    bindSingleton { IpRepository(instance(), instance()) }

    bindSingleton { HomePageInteractor(instance(), instance(), instance()) }

    bindSingleton { WeatherDetailsDtoMapper() }
    bindSingleton { CurrentDtoMapper(instance()) }
    bindSingleton { HourlyDtoMapper(instance()) }
    bindSingleton { TemperatureDtoMapper() }
    bindSingleton { FeelsLikeDtoMapper() }
    bindSingleton { DailyDtoMapper(instance(), instance()) }
    bindSingleton { WeatherDtoMapper(instance(), instance(), instance()) }
}