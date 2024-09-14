package io.github.dkexception.kmm.aqiapp.maps

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.dkexception.kmm.aqiapp.maps.data.MapData
import io.github.dkexception.kmm.aqiapp.maps.providers.MapProvider

interface IMapView {

    @Composable
    fun DefaultMapView(
        mapData: MapData,
        modifier: Modifier
    )

    @Composable
    fun MapViewForProvider(
        provider: MapProvider,
        mapData: MapData,
        modifier: Modifier
    )
}
