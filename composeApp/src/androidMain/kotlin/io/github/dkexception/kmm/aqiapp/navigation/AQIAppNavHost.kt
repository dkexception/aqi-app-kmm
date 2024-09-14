package io.github.dkexception.kmm.aqiapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.dkexception.kmm.aqiapp.maps.IMapView
import io.github.dkexception.kmm.aqiapp.maps.data.MapData
import io.github.dkexception.kmm.aqiapp.utils.Constants
import org.koin.compose.koinInject

@Composable
fun AQIAppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) = NavHost(
    modifier = modifier,
    navController = navHostController,
    startDestination = Constants.NavigationRoutes.SCREEN_404
) {

    composable(Constants.NavigationRoutes.SCREEN_404) {

        val mapView: IMapView = koinInject()

        mapView.DefaultMapView(
            mapData = MapData {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
