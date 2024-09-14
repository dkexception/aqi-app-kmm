package io.github.dkexception.kmm.aqiapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.dkexception.kmm.aqiapp.utils.Constants

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
        Text("Hello World!")
    }
}
