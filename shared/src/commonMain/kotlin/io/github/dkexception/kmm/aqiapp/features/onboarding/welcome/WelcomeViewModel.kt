package io.github.dkexception.kmm.aqiapp.features.onboarding.welcome

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseScreenEvent
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.data.preferences.AQIPreferencesKey
import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.navigation.AuthRoutes
import io.github.dkexception.kmm.aqiapp.navigation.HomeRoutes
import io.github.dkexception.kmm.aqiapp.navigation.OnboardingRoutes
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val preferencesHelper: IPreferencesHelper
) : BaseViewModel<BaseScreenEvent>() {

    init {
        navigateNext()
    }

    private fun navigateNext() = viewModelScope.launch {

        val preferencesValues: List<Boolean> = listOf(
            async {
                preferencesHelper.getBool(
                    key = AQIPreferencesKey.SP_KEY_ONBOARDING_DONE,
                    default = false
                )
            },
            async {
                preferencesHelper.getString(
                    key = AQIPreferencesKey.SP_KEY_USER_DATA
                ) != null
            }
        ).awaitAll()

        val isUserOnboarded = preferencesValues[0]

        val isUserAuthenticated = preferencesValues[1]

        val nextRoute: Any = when {
            isUserAuthenticated -> {
                HomeRoutes.HomeMain
            }

            isUserOnboarded -> {
                AuthRoutes.AuthLogin
            }

            else -> {
                OnboardingRoutes.OnboardingGuide
            }
        }

        navigator.navigateClearingStackWithObject(nextRoute)
    }
}
