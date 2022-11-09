package com.kmm.weather.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmm.weather.android.theme.WeatherKmmTheme

@Composable
fun RetryScreen(
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Something went wrong.\nPlease, check your internet connection or try again later.",
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextButton(
            onClick = onRetryClick,
            modifier = Modifier
                .defaultMinSize(minHeight = 48.dp)
                .padding(start = 4.dp, end = 4.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.15F)),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White.copy(alpha = 0.05F)),
            contentPadding = PaddingValues(start = 4.dp, end = 4.dp)
        ) {
            Text(
                text = "Retry",
                style = TextStyle(
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.White,
                        offset = Offset(3F, 3F),
                        blurRadius = 5F
                    )
                )
            )
        }
    }
}

@Preview(name = "Retry screen preview", showBackground = false)
@Composable
private fun RetryScreenPreview() {
    WeatherKmmTheme {
        RetryScreen {
            // Do nothing
        }
    }
}
