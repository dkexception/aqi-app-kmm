package io.github.dkexception.kmm.aqiapp.usecases

import io.github.dkexception.kmm.aqiapp.data.preferences.AQIPreferencesKey
import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.features.appcontent.PostWelcomeDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class InitialAppNavigation(
    private val preferencesHelper: IPreferencesHelper
) {

    suspend operator fun invoke(): PostWelcomeDestination = withContext(Dispatchers.Default) {

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

        when {
            isUserAuthenticated -> {
                PostWelcomeDestination.HOME
            }

            isUserOnboarded -> {
                PostWelcomeDestination.LOGIN
            }

            else -> {
                PostWelcomeDestination.GUIDE
            }
        }
    }
}
