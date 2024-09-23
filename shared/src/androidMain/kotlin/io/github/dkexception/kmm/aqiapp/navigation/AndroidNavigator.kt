package io.github.dkexception.kmm.aqiapp.navigation

import androidx.navigation.NavHostController
import androidx.navigation.navOptions

class AndroidNavigator : Navigator {

    private var navController: NavHostController? = null

    fun setNavController(controller: NavHostController) {
        this.navController = controller
    }

    override val isAndroid: Boolean = true

    override fun navigate(obj: Any) {
        try {
            navController?.navigate(obj)
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun navigateClearingStack(obj: Any) {
        try {
            navController?.navigate(obj) {
                popUpTo(0)
            }
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun navigatePoppingCurrent(obj: Any) {
        try {
            navController?.popBackStack()
            navController?.navigate(obj)
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun navigatePoppingUpto(obj: Any, popUptoObj: Any, inclusive: Boolean) {
        try {
            navController?.navigate(
                route = obj,
                navOptions = navOptions {
                    popUpTo(popUptoObj) {
                        this.inclusive = inclusive
                    }
                }
            )
        } catch (e: IllegalArgumentException) {
            handleIllegalNavigation()
        }
    }

    override fun canGoBack(): Boolean = navController?.previousBackStackEntry != null

    override fun goBack() {
        navController?.popBackStack()
    }

    override fun handleIllegalNavigation() {
        navController?.navigate(OtherRoutes.Invalid404)
    }
}
