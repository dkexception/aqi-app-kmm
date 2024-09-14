package io.github.dkexception.kmm.aqiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import io.github.dkexception.kmm.aqiapp.navigation.AndroidNavigator
import io.github.dkexception.kmm.aqiapp.navigation.Navigator
import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import org.koin.android.ext.android.inject

class AQIAppActivity : ComponentActivity() {

    private val navigator: Navigator by inject()

    private val snackbarHelper: ISnackbarHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            (navigator as? AndroidNavigator)?.setNavController(navController)

            MaterialTheme {
                App(
                    snackbarHelper = snackbarHelper,
                    navHostController = navController
                )
            }
        }
    }
}
