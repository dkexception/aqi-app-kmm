package io.github.dkexception.kmm.aqiapp.features.onboarding.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.dkexception.kmm.aqiapp.R
import io.github.dkexception.ui.images.DXIllustration
import io.github.dkexception.ui.theme.DXColors

@Composable
fun WelcomeScreen() = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(DXColors.screenBackground.secondary),
    contentAlignment = Alignment.Center
) {
    DXIllustration(
        illustration = R.drawable.ill_welcome_bg,
        modifier = Modifier.fillMaxSize(),
        optionalContentScale = ContentScale.FillBounds
    )
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = Modifier.size(200.dp)
    )
}

@Preview
@Composable
private fun WelcomeScreenPreview() = WelcomeScreen()
