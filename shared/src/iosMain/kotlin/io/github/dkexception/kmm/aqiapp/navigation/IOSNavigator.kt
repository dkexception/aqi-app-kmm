package io.github.dkexception.kmm.aqiapp.navigation

class IOSNavigator : Navigator {

    private var mNavigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        this.mNavigator = navigator
    }

    override val isAndroid: Boolean = false

    override fun navigate(route: String) {
        mNavigator?.navigate(route)
    }

    override fun navigateWithObject(obj: Any) {
        mNavigator?.navigateWithObject(obj)
    }

    override fun navigateClearingStack(route: String) {
        mNavigator?.navigateClearingStack(route)
    }

    override fun navigateClearingStackWithObject(obj: Any) {
        mNavigator?.navigateClearingStackWithObject(obj)
    }

    override fun navigatePoppingCurrent(route: String) {
        mNavigator?.navigatePoppingCurrent(route)
    }

    override fun navigatePoppingUpto(route: String, popUptoRoute: String, inclusive: Boolean) {
        mNavigator?.navigatePoppingUpto(route, popUptoRoute, inclusive)
    }

    override fun goBack() {
        mNavigator?.goBack()
    }

    override fun handleIllegalNavigation() {
        mNavigator?.handleIllegalNavigation()
    }
}
