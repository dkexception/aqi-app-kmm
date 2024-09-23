package io.github.dkexception.kmm.aqiapp.navigation

interface Navigator {

    val isAndroid: Boolean

    fun navigate(obj: Any)

    fun navigateClearingStack(obj: Any)

    fun navigatePoppingCurrent(obj: Any)

    fun navigatePoppingUpto(obj: Any, popUptoObj: Any, inclusive: Boolean)

    fun canGoBack(): Boolean

    fun goBack()

    fun handleIllegalNavigation()
}
