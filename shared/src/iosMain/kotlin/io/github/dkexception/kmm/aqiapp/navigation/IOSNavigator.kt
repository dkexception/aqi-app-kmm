package io.github.dkexception.kmm.aqiapp.navigation

class IOSNavigator : Navigator {

    private var mNavigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        this.mNavigator = navigator
    }

    override val isAndroid: Boolean = false

    override fun navigate(obj: Any) {
        mNavigator?.navigate(obj)
    }

    override fun navigateClearingStack(obj: Any) {
        mNavigator?.navigateClearingStack(obj)
    }

    override fun navigatePoppingCurrent(obj: Any) {
        mNavigator?.navigatePoppingCurrent(obj)
    }

    override fun navigatePoppingUpto(obj: Any, popUptoObj: Any, inclusive: Boolean) {
        mNavigator?.navigatePoppingUpto(obj, popUptoObj, inclusive)
    }

    override fun canGoBack(): Boolean = true == mNavigator?.canGoBack()

    override fun goBack() {
        mNavigator?.goBack()
    }

    override fun handleIllegalNavigation() {
        mNavigator?.handleIllegalNavigation()
    }
}
