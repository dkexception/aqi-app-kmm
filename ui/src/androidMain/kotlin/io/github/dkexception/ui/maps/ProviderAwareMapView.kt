package io.github.dkexception.ui.maps

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.dkexception.ui.maps.data.MapData
import io.github.dkexception.ui.maps.providers.GoogleMapView
import io.github.dkexception.ui.maps.providers.MapMyIndiaMapView
import io.github.dkexception.ui.maps.providers.MapProvider
import io.github.dkexception.ui.maps.providers.OpenStreetMapView

internal class ProviderAwareMapView : IMapView {

    @Composable
    override fun DefaultMapView(
        mapData: MapData,
        modifier: Modifier,
    ) = MapViewByProvider(
        provider = MapProvider.getDefault(),
        mapData = mapData,
        modifier = modifier
    )

    @Composable
    override fun MapViewForProvider(
        provider: MapProvider,
        mapData: MapData,
        modifier: Modifier
    ) = MapViewByProvider(provider = provider, mapData = mapData, modifier = modifier)

    @Composable
    private fun MapViewByProvider(
        provider: MapProvider,
        mapData: MapData,
        modifier: Modifier
    ) = when (provider) {
        MapProvider.GOOGLE -> GoogleMapView(
            mapData = mapData,
            modifier = modifier
        )

        MapProvider.OPEN_STREET_MAP -> OpenStreetMapView(
            mapData = mapData,
            modifier = modifier
        )

        MapProvider.MAP_MY_INDIA -> MapMyIndiaMapView(
            mapData = mapData,
            modifier = modifier
        )
    }
}
