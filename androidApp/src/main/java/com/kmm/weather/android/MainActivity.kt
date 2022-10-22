package com.kmm.weather.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.weather.home_page.HomePageUiState
import com.kmm.weather.home_page.WeatherModel

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*val scope = rememberCoroutineScope()
                    val text = remember { mutableStateOf("Loading") }
                    LaunchedEffect(true) {
                        scope.launch {
                            text.value = Greeting().greeting()
                        }
                    }*/
                    Greeting("Text from onCreate(..)", viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String, viewModel: MainViewModel) {
    /*//viewModel.fetchWeather()
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is HomePageUiState.Loading -> {
            Text(text = "Loading")
        }
        is HomePageUiState.Success -> WeatherScreen(uiState.weatherModel)
        is HomePageUiState.Failure -> {
//            RetryScreen { viewModel.retry() }
//            ErrorDialog(uiState.message)
            Text(text = "Error: ${uiState.message}")
        }
    }
    val uiState = remember { viewModel.uiState }*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val state = viewModel.uiState.collectAsState().value) {
            HomePageUiState.Loading -> {
                Text(text = "Loading")
                viewModel.fetchWeather()
            }
            is HomePageUiState.Success -> {
                val weatherModel: WeatherModel = state.weatherModel
                val city = weatherModel.city
                Text(text = "City: $city")
                Spacer(modifier = Modifier.size(height = 16.dp, width = 0.dp))
                Button(onClick = { viewModel.retry() }) {
                    Text(text = "Fetch one more")
                }
            }
            is HomePageUiState.Failure -> {
                Text(text = state.message)
                Button(onClick = { viewModel.retry() }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        val viewModel = MainViewModel()
        Greeting("Hello, Android!", viewModel)
    }
}
