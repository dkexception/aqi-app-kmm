package io.github.dkexception.kmm.aqiapp.features.onboarding.guide

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.data.preferences.AQIPreferencesKey
import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.navigation.AuthRoutes
import io.github.dkexception.kmm.aqiapp.navigation.HomeRoutes
import kotlinx.coroutines.launch

class GuideViewModel(
    private val preferencesHelper: IPreferencesHelper
) : BaseViewModel<GuideEvent>(), IGuideViewModel {

    override fun onEvent(guideEvent: GuideEvent) {
        when (guideEvent) {

            GuideEvent.OnGetStartedClicked -> navigateNext()

            GuideEvent.OnSkipClicked -> navigateNext()
        }
    }

    private fun navigateNext() = viewModelScope.launch {

        // Set user seen onboarding
        preferencesHelper.putBool(
            key = AQIPreferencesKey.SP_KEY_ONBOARDING_DONE,
            value = true
        )

        val isUserAuthenticated: Boolean = preferencesHelper.getString(
            key = AQIPreferencesKey.SP_KEY_USER_DATA
        ) != null

        // And navigate further
        if (isUserAuthenticated) {
            navigator.navigateClearingStack(HomeRoutes.HomeMain)
        } else {
            navigator.navigate(AuthRoutes.AuthLogin)
        }
    }
}
