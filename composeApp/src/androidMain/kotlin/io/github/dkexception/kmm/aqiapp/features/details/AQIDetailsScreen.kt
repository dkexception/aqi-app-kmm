package io.github.dkexception.kmm.aqiapp.features.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.dkexception.kmm.aqiapp.domain.models.aqi.AirQualityData
import io.github.dkexception.ui.indicators.DXPullToRefreshContainer
import io.github.dkexception.ui.scaffold.DXScaffold
import io.github.dkexception.ui.theme.DXPaddings

@Composable
fun AQIDetailsScreen(
    state: AQIDetailsScreenState,
    onEvent: (AQIDetailsEvent) -> Unit
) = AQIDetailsScreenContent(state = state, onEvent = onEvent)

@Composable
private fun AQIDetailsScreenContent(
    state: AQIDetailsScreenState,
    onEvent: (AQIDetailsEvent) -> Unit
) = DXScaffold(modifier = Modifier.fillMaxSize()) {

    DXPullToRefreshContainer(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = state.isLoading,
        onPulled = {
            onEvent(AQIDetailsEvent.OnPulledToRefresh)
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(DXPaddings.default)
        ) {

            item {
                Text(text = state.aqiData.city.toString())
            }
        }
    }
}

@Preview
@Composable
private fun AQIDetailsScreenPreview() = AQIDetailsScreen(
    state = AQIDetailsScreenState(
        aqiData = AirQualityData.PREVIEW_DATA
    )
) { }
