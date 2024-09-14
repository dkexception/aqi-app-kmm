package io.github.dkexception.kmm.aqiapp.navigation

import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import io.github.dkexception.kmm.aqiapp.utils.Constants

class AndroidNavigator : Navigator {

    private var navController: NavHostController? = null

    fun setNavController(controller: NavHostController) {
        this.navController = controller
    }

    override val isAndroid: Boolean = true

    override fun navigate(route: String) {
        try {
            navController?.navigate(route)
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun navigateClearingStack(route: String) {
        try {
            navController?.navigate(route) {
                popUpTo(0)
            }
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun navigatePoppingCurrent(route: String) {
        try {
            navController?.popBackStack()
            navController?.navigate(route)
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun navigatePoppingUpto(route: String, popUptoRoute: String, inclusive: Boolean) {
        try {
            navController?.navigate(
                route = route,
                navOptions = navOptions {
                    popUpTo(popUptoRoute) {
                        this.inclusive = inclusive
                    }
                }
            )
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun goBack() {
        navController?.popBackStack()
    }

    override fun handleIllegalNavigation() {
        navController?.navigate(Constants.NavigationRoutes.SCREEN_404)
    }
}
