package com.kmm.weather.android.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmm.weather.presentation.DayForecast

@Composable
fun dailyForecasts(
    dailyForecasts: List<DayForecast>,
    onDayForecastClick: (DayForecast) -> Unit
) = getDayNightTemperatureMaxLengths(dailyForecasts).let { (dayTempMaxLength, nightTempMaxLength) ->
    dailyForecasts.map { dayForecast ->
        val datNightTemp = "${getDayTemperature(dayForecast.day, dayTempMaxLength)} / ${getNightTemperature(dayForecast.night, nightTempMaxLength)}"
        Spacer(Modifier.height(8.dp))
        DayTextButton(dayForecast.dayName, datNightTemp) {
            onDayForecastClick(dayForecast)
        }
    }
}

private fun getDayNightTemperatureMaxLengths(dailyForecasts: List<DayForecast>): Pair<Int, Int> {
    var dayTempMaxStringLength = 0
    var nightTempMaxStringLength = 0
    dailyForecasts.map { dayForecast ->
        val dayTempLength = dayForecast.day.toString().length
        dayTempMaxStringLength = Math.max(dayTempMaxStringLength, dayTempLength)

        val nightTempLength = dayForecast.night.toString().length
        nightTempMaxStringLength = Math.max(dayTempMaxStringLength, nightTempLength)
    }
    return Pair(dayTempMaxStringLength, nightTempMaxStringLength)
}

@Composable
private fun getDayTemperature(temperature: Float, maxStringLength: Int): String {
    val currentDayTempLength = temperature.toString().length
    var dayValue = "$temperature °C"
    if (currentDayTempLength < maxStringLength) {
        val lengthDifference = maxStringLength - currentDayTempLength
        for (spaceCount in 0..lengthDifference) {
            dayValue += " "
        }
    }
    return dayValue
}

@Composable
private fun getNightTemperature(temperature: Float, maxStringLength: Int): String {
    val currentNightTempLength = temperature.toString().length
    var nightValue = "$temperature °C"
    if (currentNightTempLength < maxStringLength) {
        val lengthDifference = maxStringLength - currentNightTempLength
        var spaces = ""
        for (spaceCount in 0..lengthDifference) {
            spaces += " "
        }
        nightValue = spaces + nightValue
    }
    return nightValue
}
