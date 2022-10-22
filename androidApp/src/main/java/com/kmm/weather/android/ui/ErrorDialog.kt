package com.kmm.weather.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.weather.android.theme.WeatherKmmTheme

@Composable
fun ErrorDialog(message: String) {
    val isDialogOpened = remember { mutableStateOf(true) }
    if (isDialogOpened.value) {
        AlertDialog(
            modifier = Modifier.padding(8.dp),
            onDismissRequest = {
                isDialogOpened.value = false
            },
            title = {
                Title()
            },
            text = {
                Description(message)
            },
            confirmButton = {
                OkButton { isDialogOpened.value = false }
            }
        )
    }
}

@Composable
private fun Title() = Text(
    modifier = Modifier.fillMaxSize(),
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    text = "Error occured",
    textAlign = TextAlign.Center,
)

@Composable
private fun Description(message: String) = Text(fontSize = 14.sp, text = message)

@Composable
private fun OkButton(onClick: () -> Unit) = TextButton(
    border = BorderStroke(0.5.dp, Color.Black),
    modifier = Modifier.padding(8.dp),
    onClick = onClick
) {
    Text(
        color = Color.Black,
        text = "Ok"
    )
}

@Preview(name = "Error dialog preview", showBackground = true)
@Composable
private fun ErrorDialogPreview() {
    WeatherKmmTheme {
        ErrorDialog("Something has happened")
    }
}
