package io.github.dkexception.kmm.aqiapp.navigation

interface Navigator {

    val isAndroid: Boolean

    fun navigate(route: String)

    fun navigateClearingStack(route: String)

    fun navigatePoppingCurrent(route: String)

    fun navigatePoppingUpto(route: String, popUptoRoute: String, inclusive: Boolean)

    fun goBack()

    fun handleIllegalNavigation()
}
