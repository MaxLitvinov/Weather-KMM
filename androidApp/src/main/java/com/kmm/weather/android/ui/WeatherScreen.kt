package com.kmm.weather.android.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.weather.android.theme.WeatherKmmTheme
import com.kmm.weather.presentation.WeatherModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeatherScreen(
    model: WeatherModel,
    onDayForecastClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textStyle = TextStyle(
            color = Color.White,
            shadow = Shadow(
                color = Color.White,
                offset = Offset(3F, 3F),
                blurRadius = 5F
            )
        )

        CityButton(model, textStyle)
        Spacer(Modifier.height(8.dp))
        Image(model)
        Spacer(Modifier.height(8.dp))
        Temperature(model, textStyle)
        Spacer(Modifier.height(8.dp))
        Description(model, textStyle)
        Spacer(Modifier.height(24.dp))
        dailyForecasts(model.dailyForecasts) { dayForecast ->
            onDayForecastClick(0)
        }
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun CityButton(model: WeatherModel, textStyle: TextStyle) {
    val context = LocalContext.current
    TextButton(
        onClick = {
            // TODO: Stub. Navigate user to Google maps page
            Toast.makeText(context, "Next page doesn't exist yet", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .defaultMinSize(minHeight = 48.dp)
            .padding(start = 4.dp, end = 4.dp)
    ) {
        Text(
            text = model.city,
            style = textStyle.copy(fontSize = 28.sp, fontWeight = FontWeight.Bold),
        )
    }
}

@Composable
private fun Image(model: WeatherModel) {
    GlideImage(
        imageModel = { model.iconUrl },
        modifier = Modifier.height(200.dp),
        imageOptions = ImageOptions(
            contentDescription = model.weatherDescription
        ),
        previewPlaceholder = 0,//R.drawable.ic_launcher_foreground,
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        },
        failure = {
            androidx.compose.foundation.Image(
                painter = painterResource(0),//,painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = model.weatherDescription,
                modifier = Modifier.height(200.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
    )
}

@Composable
private fun Temperature(model: WeatherModel, textStyle: TextStyle) {
    Text(
        style = textStyle.copy(fontSize = 64.sp, fontWeight = FontWeight.Bold),
        text = "${model.temperature}°C"
    )
}

@Composable
private fun Description(model: WeatherModel, textStyle: TextStyle) {
    Text(
        style = textStyle,
        text = model.weatherDescription
    )
}

@Preview(name = "Weather screen preview", showBackground = false)
@Composable
private fun WeatherScreenPreview() {
    val fakeModel = WeatherModel(
        city = "Kharkiv",
        iconUrl = "",
        temperature = "20.07",
        weatherDescription = "Rainy",
        dailyForecasts = listOf()
    )
    WeatherKmmTheme {
        WeatherScreen(
            model = fakeModel,
            onDayForecastClick = {}
        )
    }
}
