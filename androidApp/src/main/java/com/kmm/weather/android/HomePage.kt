package com.kmm.weather.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.kmm.weather.android.theme.HomePageBlue
import com.kmm.weather.android.theme.HomePagePurple
import com.kmm.weather.android.ui.ErrorDialog
import com.kmm.weather.android.ui.ProgressDialog
import com.kmm.weather.android.ui.RetryScreen
import com.kmm.weather.android.ui.WeatherScreen
import com.kmm.weather.presentation.HomePageUiState

@Composable
fun HomePage(
    uiState: HomePageUiState,
    onDayForecastClick: (Long) -> Unit,
    onRetryClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors = listOf(HomePageBlue, HomePagePurple),
                startY = 0F,
                endY = getDisplayHeight()
            )
        )
        .verticalScroll(state = rememberScrollState()),
    verticalArrangement = Arrangement.Center
) {
    when (uiState) {
        HomePageUiState.Loading -> ProgressDialog()
        is HomePageUiState.Success -> WeatherScreen(
            model = uiState.weatherModel,
            onDayForecastClick = onDayForecastClick
        )
        is HomePageUiState.Failure -> {
            RetryScreen(onRetryClick = onRetryClick)
            ErrorDialog(message = uiState.message)
        }
    }
}

@Composable
private fun getDisplayHeight(): Float = with(LocalDensity.current) {
    return LocalConfiguration.current.screenHeightDp.dp.toPx()
}
