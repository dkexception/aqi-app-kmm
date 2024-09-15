package io.github.dkexception.kmm.aqiapp.features.onboarding.welcome

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseScreenEvent
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.navigation.OnboardingRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeViewModel(
//    private val dataStore: DataStore
) : BaseViewModel<BaseScreenEvent>() {

//    private val isUserOnboarded: Boolean = dataStore.getBoolean(
//        key = Constants.SP_KEY_ONBOARDING_DONE,
//        default = false
//    )
//
//    private val isUserAuthenticated: Boolean = dataStore.containsKey(
//        key = Constants.SP_KEY_USER_DATA
//    )

    init {
        navigateNext()
    }

    private fun navigateNext() = viewModelScope.launch {

        delay(3000)

//        val nextRoute = when {
//            isUserAuthenticated -> {
//                NavRoute.HOME.ROOT
//            }
//
//            isUserOnboarded -> {
//                NavRoute.AUTH.ROOT
//            }
//
//            else -> {
//                NavRoute.ONBOARDING.GUIDE
//            }
//        }

        val nextRoute = OnboardingRoutes.OnboardingGuide

        navigator.navigateClearingStackWithObject(nextRoute)
    }
}
