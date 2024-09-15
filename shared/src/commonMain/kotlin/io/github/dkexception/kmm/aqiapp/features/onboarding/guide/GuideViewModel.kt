package io.github.dkexception.kmm.aqiapp.features.onboarding.guide

import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.navigation.AuthRoutes
import io.github.dkexception.kmm.aqiapp.navigation.HomeRoutes
import kotlinx.coroutines.CoroutineScope

class GuideViewModel(
//    private val dataStore: DataStore
) : BaseViewModel<GuideEvent>(), IGuideViewModel {

//    private val isUserAuthenticated: Boolean = dataStore.containsKey(
//        key = Constants.SP_KEY_USER_DATA
//    )

    override fun onEvent(guideEvent: GuideEvent) {
        when (guideEvent) {

            GuideEvent.OnGetStartedClicked -> navigateNext()

            GuideEvent.OnSkipClicked -> navigateNext()
        }
    }

    private fun navigateNext() {

        // Set user seen onboarding
//        dataStore.saveBoolean(
//            key = Constants.SP_KEY_ONBOARDING_DONE,
//            value = true
//        )

        // And navigate further
//        if (isUserAuthenticated) { // todo
        if (false) {
            navigator.navigateClearingStackWithObject(HomeRoutes.HomeMain)
        } else {
            navigator.navigateWithObject(AuthRoutes.AuthLogin)
        }
    }
}
