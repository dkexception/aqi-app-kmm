package io.github.dkexception.kmm.aqiapp.extensions

import androidx.navigation.NavHostController

fun NavHostController.canGoBack(): Boolean = previousBackStackEntry != null
