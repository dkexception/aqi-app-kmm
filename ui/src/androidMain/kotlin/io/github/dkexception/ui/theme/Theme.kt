package io.github.dkexception.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.dkexception.ui.theme.generated.DarkColorsM3
import io.github.dkexception.ui.theme.generated.LightColorsM3

@Composable
fun DXTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (!useDarkTheme) {
        LightColorsM3
    } else {
        DarkColorsM3
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
