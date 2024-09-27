@file:Suppress("unused")

package io.github.dkexception.kmm.aqiapp.utils

import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.features.appcontent.AppContentViewModel
import io.github.dkexception.kmm.aqiapp.features.appcontent.IAppContentViewModel
import io.github.dkexception.kmm.aqiapp.features.auth.login.ILoginViewModel
import io.github.dkexception.kmm.aqiapp.features.auth.login.LoginViewModel
import io.github.dkexception.kmm.aqiapp.features.home.HomeViewModel
import io.github.dkexception.kmm.aqiapp.features.home.IHomeViewModel
import io.github.dkexception.kmm.aqiapp.features.more.IMoreListViewModel
import io.github.dkexception.kmm.aqiapp.features.more.MoreListViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.IGuideViewModel
import io.github.dkexception.kmm.aqiapp.navigation.Navigator
import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import io.github.dkexception.kmm.aqiapp.usecases.InitialAppNavigation
import io.github.dkexception.kmm.aqiapp.validators.ISingleStringValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class IOSHelpers : KoinComponent {

    val navigator: Navigator by inject()

    val snackbarHelper: ISnackbarHelper by inject()

    val coroutineScope: CoroutineScope by inject()

    private val preferencesHelper: IPreferencesHelper by inject()

    private val initialAppNavigation: InitialAppNavigation by inject()

    fun cancel(coroutineScope: CoroutineScope) {
        coroutineScope.cancel()
    }

    fun provideAppContentViewModel(): IAppContentViewModel =
        AppContentViewModel(initialAppNavigation)

    fun provideGuideViewModel(): IGuideViewModel = GuideViewModel(preferencesHelper)

    private val emailValidator: ISingleStringValidator by inject(named("email"))
    private val passwordValidator: ISingleStringValidator by inject(named("password"))
    fun provideLoginViewModel(): ILoginViewModel = LoginViewModel(
        preferencesHelper = preferencesHelper,
        emailValidator = emailValidator,
        passwordValidator = passwordValidator
    )

    fun provideHomeViewModel(): IHomeViewModel = HomeViewModel(preferencesHelper)

    fun provideMoreListViewModel(): IMoreListViewModel = MoreListViewModel(preferencesHelper)
}
