package io.github.dkexception.kmm.aqiapp.features.onboarding.welcome

import io.github.dkexception.kmm.aqiapp.base.BaseScreenEvent
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.features.appcontent.PostWelcomeDestination
import io.github.dkexception.kmm.aqiapp.navigation.AuthRoutes
import io.github.dkexception.kmm.aqiapp.navigation.HomeRoutes
import io.github.dkexception.kmm.aqiapp.navigation.OnboardingRoutes
import io.github.dkexception.kmm.aqiapp.navigation.OtherRoutes
import io.github.dkexception.kmm.aqiapp.usecases.InitialAppNavigation
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val initialAppNavigation: InitialAppNavigation
) : BaseViewModel<BaseScreenEvent>() {

    init {
        navigateNext()
    }

    private fun navigateNext() = mScope.launch {
        navigator.navigateClearingStack(
            when (initialAppNavigation()) {
                PostWelcomeDestination.UNKNOWN -> OtherRoutes.Invalid404
                PostWelcomeDestination.GUIDE -> OnboardingRoutes.OnboardingGuide
                PostWelcomeDestination.LOGIN -> AuthRoutes.AuthLogin
                PostWelcomeDestination.HOME -> HomeRoutes.HomeMain
            }
        )
    }
}
