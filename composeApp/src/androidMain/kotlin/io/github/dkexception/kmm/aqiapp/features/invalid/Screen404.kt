package io.github.dkexception.kmm.aqiapp.features.invalid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Screen404() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) {
    Text(
        text = "404 Not Found!",
        style = MaterialTheme.typography.headlineLarge
    )
}

@Preview
@Composable
private fun Screen404Preview() = Screen404()
