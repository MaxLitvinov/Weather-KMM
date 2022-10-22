package com.kmm.weather.home_page.open_weather_map

import com.kmm.weather.network.NetworkClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class WeatherForecastRepository constructor(
    /*private val api: OpenWeatherMapApi,
    private val mapper: WeatherDtoMapper*/
    override val di: DI
) : DIAware {

    // https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
    // https://api.openweathermap.org/data/3.0/onecall?lat=51.5055&lon=31.2933&appid=978539e18a215484b0146ed80b932145

    // https://api.openweathermap.org/data/2.5/onecall?&lang=en&units=metric&appid=978539e18a215484b0146ed80b932145
    // https://api.openweathermap.org/data/2.5/onecall?lat=51.5055&lon=31.2933&lang=en&units=metric&appid=978539e18a215484b0146ed80b932145
    val networkClient: NetworkClient by instance()

    suspend fun fetchWeather(lat: String, lon: String): WeatherDomainResult {
        val apiKey = "978539e18a215484b0146ed80b932145"
        val url = "https://api.openweathermap.org/data/3.0/onecall?lat=$lat&lon=$lon&appid=$apiKey"
        val url2 =
            "https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lon&lang=en&units=metric&appid=978539e18a215484b0146ed80b932145"
        val request = networkClient.getClient().get(url2)
        try {
            val response = request.body<WeatherDto>()
            val result = WeatherDomainResult.Success(
                WeatherDomainModel(
                    latitude = response.latitude,
                    longitude = response.longitude,
                    timezone = response.timezone,
                    timezoneOffset = response.timezoneOffset,
                    currentWeather = CurrentWeatherDomainModel(
                        currentTime = response.current.currentTime,
                        sunriseTime = response.current.sunriseTime,
                        sunsetTime = response.current.sunsetTime,
                        temperature = response.current.temperature,
                        feelsLike = response.current.feelsLike,
                        pressure = response.current.pressure,
                        humidity = response.current.humidity,
                        dewPoint = response.current.dewPoint,
                        clouds = response.current.clouds,
                        uvIndex = response.current.uvIndex,
                        visibility = response.current.visibility,
                        windDeg = response.current.windDeg,
                        windGust = response.current.windGust,
                        windSpeed = response.current.windSpeed,
                        lastHourRainVolume = response.current.rain?.lastHourVolume,
                        lastHourSnowVolume = response.current.snow?.lastHourVolume,
                        weatherDetails = WeatherDetailsDomainModel(
                            id = response.current.weather.first().id,
                            mainDescription = response.current.weather.first().mainDescription,
                            detailedDescription = response.current.weather.first().description,
                            iconUrl = response.current.weather.first().icon
                        ),
                    ),
                    hourlyForecasts = emptyList(),
                    dailyForecasts = emptyList(),
                )
            )
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return WeatherDomainResult.Failure("Error message from WeatherForecastRepository")
    }
}
