package com.kmm.weather.home_page.open_weather_map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param latitude Geographical coordinates of the location (latitude)
 * @param longitude Geographical coordinates of the location (longitude)
 * @param timezone Timezone name for the requested location
 * @param timezoneOffset Shift in seconds from UTC
 * @param current Current weather data API response
 * @param hourlyForecasts Hourly forecast weather data API response
 * @param dailyForecasts Daily forecasts weather data API response
 *
 * @see <a href="https://openweathermap.org/api/one-call-3">OpenWeather One Call API 3.0.</a>
 */
@Serializable
data class WeatherDto(
    @SerialName("lat")
    val latitude: Float,
    @SerialName("lon")
    val longitude: Float,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_offset")
    val timezoneOffset: Int,
    @SerialName("current")
    val current: CurrentDto,
    @SerialName("hourly")
    val hourlyForecasts: List<HourlyDto>? = null,
    @SerialName("daily")
    val dailyForecasts: List<DailyDto>
)

/**
 * This is the current weather data.
 *
 * @param currentTime Current time, Unix, UTC
 * @param sunriseTime Sunrise time, Unix, UTC
 * @param sunsetTime Sunset time, Unix, UTC
 * @param temperature Temperature. Units - default: kelvin, metric: Celsius, imperial: Fahrenheit. How to change units used
 * @param feelsLike Temperature. This temperature parameter accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param pressure Atmospheric pressure on the sea level, hPa
 * @param humidity Humidity, %
 * @param dewPoint Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param clouds Cloudiness, %
 * @param uvIndex Current UV index
 * @param visibility Average visibility, metres. The maximum value of the visibility is 10km
 * @param windSpeed Wind speed. Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
 * @param windGust (where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
 * @param windDeg Wind direction, degrees (meteorological)
 * @param rain rain.1h (where available) Rain volume for last hour, mm
 * @param snow snow.1h (where available) Snow volume for last hour, mm
 * @param weather weather details
 */
@Serializable
data class CurrentDto(
    @SerialName("dt")
    val currentTime: Long,
    @SerialName("sunrise")
    val sunriseTime: Long,
    @SerialName("sunset")
    val sunsetTime: Long,
    @SerialName("temp")
    val temperature: Float,
    @SerialName("feels_like")
    val feelsLike: Float,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("dew_point")
    val dewPoint: Float,
    @SerialName("clouds")
    val clouds: Int,
    @SerialName("uvi")
    val uvIndex: Float,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_gust")
    val windGust: Float,
    @SerialName("wind_speed")
    val windSpeed: Float,
    @SerialName("rain")
    val rain: RainDto? = null,
    @SerialName("snow")
    val snow: SnowDto? = null,
    @SerialName("weather")
    val weather: List<WeatherDetailsDto>
)

/**
 * @param id Weather condition id
 * @param mainDescription Group of weather parameters (Rain, Snow, Extreme etc.)
 * @param description Weather condition within the group
 * @param icon Weather icon id
 */
@Serializable
data class WeatherDetailsDto(
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val mainDescription: String,
    @SerialName("description")
    val description: String,
    @SerialName("icon")
    val icon: String
)

/**
 * @param time Time of the forecasted data, Unix, UTC
 * @param temperature Temperature. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param feelsLike Temperature. This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param pressure Atmospheric pressure on the sea level, hPa
 * @param humidity Humidity, %
 * @param dewPoint Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param uvIndex UV index
 * @param clouds Cloudiness, %
 * @param visibility Average visibility, metres. The maximum value of the visibility is 10km
 * @param windSpeed Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.How to change units used
 * @param windDeg Wind direction, degrees (meteorological)
 * @param windGust (where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
 * @param weather Weather details
 * @param pop Probability of precipitation. The values of the parameter vary between 0 and 1, where 0 is equal to 0%, 1 is equal to 100%
 */
@Serializable
data class HourlyDto(
    @SerialName("dt")
    val time: Long,
    @SerialName("temp")
    val temperature: Float,
    @SerialName("feels_like")
    val feelsLike: Float,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("dew_point")
    val dewPoint: Float,
    @SerialName("uvi")
    val uvIndex: Float,
    @SerialName("clouds")
    val clouds: Int,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("wind_speed")
    val windSpeed: Float,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_gust")
    val windGust: Float,
    @SerialName("weather")
    val weather: List<WeatherDetailsDto>,
    @SerialName("pop")
    val pop: Float
)

/**
 * Daily forecast weather data
 *
 * @param time Time of the forecasted data, Unix, UTC
 * @param sunriseTime Sunrise time, Unix, UTC
 * @param sunsetTime Sunset time, Unix, UTC
 * @param moonriseTime The time of when the moon rises for this day, Unix, UTC
 * @param moonsetTime The time of when the moon sets for this day, Unix, UTC
 * @param moonPhase Moon phase. 0 and 1 are 'new moon', 0.25 is 'first quarter moon', 0.5 is 'full moon' and 0.75 is 'last quarter moon'. The periods in between are called 'waxing crescent', 'waxing gibous', 'waning gibous', and 'waning crescent', respectively.
 * @param temperature Units – default: kelvin, metric: Celsius, imperial: Fahrenheit
 * @param feelsLike This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param pressure Atmospheric pressure on the sea level, hPa
 * @param humidity Humidity, %
 * @param dewPoint Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 * @param windSpeed Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
 * @param windDeg Wind direction, degrees (meteorological)
 * @param windGust (where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
 * @param clouds Cloudiness, %
 * @param uvIndex The maximum value of UV index for the day
 * @param pop Probability of precipitation. The values of the parameter vary between 0 and 1, where 0 is equal to 0%, 1 is equal to 100%
 * @param rain (where available) Precipitation volume, mm
 * @param snow (where available) Snow volume, mm
 * @param weather Weather details
 */
@Serializable
data class DailyDto(
    @SerialName("dt")
    val time: Long,
    @SerialName("sunrise")
    val sunriseTime: Long,
    @SerialName("sunset")
    val sunsetTime: Long,
    @SerialName("moonrise")
    val moonriseTime: Long,
    @SerialName("moonset")
    val moonsetTime: Long,
    @SerialName("moon_phase")
    val moonPhase: Float,
    @SerialName("temp")
    val temperature: TemperatureDto,
    @SerialName("feels_like")
    val feelsLike: FeelsLikeDto,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("dew_point")
    val dewPoint: Float,
    @SerialName("wind_speed")
    val windSpeed: Float,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_gust")
    val windGust: Float,
    @SerialName("clouds")
    val clouds: Int,
    @SerialName("uvi")
    val uvIndex: Float,
    @SerialName("pop")
    val pop: Float,
    @SerialName("rain")
    val rain: Float? = null,
    @SerialName("snow")
    val snow: Float? = null,
    @SerialName("weather")
    val weather: List<WeatherDetailsDto>
)

/**
 * Units – default: kelvin, metric: Celsius, imperial: Fahrenheit
 *
 * @param morning Morning temperature
 * @param day Day temperature
 * @param evening Evening temperature
 * @param night Night temperature
 * @param min Min daily temperature
 * @param max Max daily temperature
 *
 * @see <a href="https://openweathermap.org/api/one-call-3#data">How to change unit s used</a>
 */
@Serializable
data class TemperatureDto(
    @SerialName("morn")
    val morning: Float,
    @SerialName("day")
    val day: Float,
    @SerialName("eve")
    val evening: Float,
    @SerialName("night")
    val night: Float,
    @SerialName("min")
    val min: Float,
    @SerialName("max")
    val max: Float
)

/**
 * This accounts for the human perception of weather.
 *
 * Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
 *
 * @param morning Morning temperature
 * @param day Day temperature
 * @param evening Evening temperature
 * @param night Night temperature
 *
 * @see <a href="https://openweathermap.org/api/one-call-3#data">How to change units used</a>
 */
@Serializable
data class FeelsLikeDto(
    @SerialName("day")
    val day: Float,
    @SerialName("night")
    val night: Float,
    @SerialName("eve")
    val evening: Float,
    @SerialName("morn")
    val morning: Float
)

/**
 * Rain volume, mm
 *
 * @param lastHourVolume (where available) Rain volume for last hour, mm
 */
@Serializable
data class RainDto(
    @SerialName("1h")
    val lastHourVolume: Float
)

/**
 * Snow volume, mm
 *
 * @param lastHourVolume (where available) Snow volume for last hour, mm
 */
@Serializable
data class SnowDto(
    @SerialName("1h")
    val lastHourVolume: Float
)
