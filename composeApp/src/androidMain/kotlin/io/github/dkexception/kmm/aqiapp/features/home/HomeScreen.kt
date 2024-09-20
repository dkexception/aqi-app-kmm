package io.github.dkexception.kmm.aqiapp.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.dkexception.kmm.aqiapp.di.initKoin
import io.github.dkexception.kmm.aqiapp.di.mapsModule
import io.github.dkexception.kmm.aqiapp.domain.models.aqi.AirQualityData
import io.github.dkexception.ui.indicators.DXPullToRefreshContainer
import io.github.dkexception.ui.scaffold.DXScaffold
import io.github.dkexception.ui.theme.DXColors
import io.github.dkexception.ui.theme.DXPaddings
import io.github.dkexception.ui.theme.DXTheme
import io.github.dkexception.ui.theme.headline1
import io.github.dkexception.ui.theme.large
import io.github.dkexception.ui.theme.regular
import org.koin.android.ext.koin.androidContext

@Composable
fun HomeScreen(
    state: HomeScreenState,
//    aqiIAQIDetailsCard: IAQIDetailsCard,
    onEvent: (HomeEvent) -> Unit
) = HomeScreenContent(
    state = state,
//    aqiIAQIDetailsCard = aqiIAQIDetailsCard,
    onEvent = onEvent
)

@Composable
private fun HomeScreenContent(
    state: HomeScreenState,
//    aqiIAQIDetailsCard: IAQIDetailsCard,
    onEvent: (HomeEvent) -> Unit
) = DXScaffold(modifier = Modifier.fillMaxSize()) {

    DXPullToRefreshContainer(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = state.isLoading,
        onPulled = {
            onEvent(HomeEvent.OnPulledToRefresh)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(DXPaddings.default)
        ) {

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = "Welcome \uD83D\uDC4B",
                            style = regular(),
                            color = DXColors.text.light,
                            textAlign = TextAlign.Start
                        )

                        Spacer(modifier = Modifier.height(DXPaddings.small))

                        Text(
                            text = state.userName,
                            style = headline1(),
                            color = DXColors.text.dark,
                            textAlign = TextAlign.Start
                        )
                    }

                    IconButton(
                        onClick = {
                            onEvent(HomeEvent.OnSettingsClicked)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "More",
                            tint = DXColors.primary.default
                        )
                    }
                }

                Spacer(modifier = Modifier.height(DXPaddings.xLarge))
            }

            item {
                if (!state.isLoading && state.aqiData != null) {
                    Text(
                        text = "AQI data near you",
                        style = large(),
                        color = DXColors.text.dark,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(DXPaddings.small))
//                    aqiIAQIDetailsCard.AQIDetailsCard(
//                        aqiData = state.aqiData
//                    ) {
//                        onEvent(HomeEvent.OnAQICardClicked)
//                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() = DXTheme {

    val context = LocalContext.current

    initKoin {
        androidContext(context)
        modules(mapsModule)
    }

    HomeScreen(
        state = HomeScreenState(
            userName = "Dhanesh",
            aqiData = AirQualityData(),
            isLoading = false
        ),
//    aqiIAQIDetailsCard = { _, _ ->
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .background(Color.Cyan)
//        )
//    }
    ) {}
}
