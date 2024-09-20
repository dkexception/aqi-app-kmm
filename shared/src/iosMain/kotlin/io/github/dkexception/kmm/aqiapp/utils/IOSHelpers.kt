package io.github.dkexception.kmm.aqiapp.utils

import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.IGuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.welcome.WelcomeViewModel
import io.github.dkexception.kmm.aqiapp.navigation.Navigator
import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IOSHelpers : KoinComponent {

    @Suppress("MemberVisibilityCanBePrivate") // Used in iOS
    val navigator: Navigator by inject()

    @Suppress("MemberVisibilityCanBePrivate") // Used in iOS
    val snackbarHelper: ISnackbarHelper by inject()

    @Suppress("MemberVisibilityCanBePrivate") // Used in iOS
    val coroutineScope: CoroutineScope by inject()

    private val preferencesHelper: IPreferencesHelper by inject()

    fun cancel(coroutineScope: CoroutineScope) {
        coroutineScope.cancel()
    }

    fun provideWelcomeViewModel() = WelcomeViewModel(preferencesHelper)

    fun provideGuideViewModel(
        coroutineScope: CoroutineScope
    ): IGuideViewModel = GuideViewModel(preferencesHelper)
}
