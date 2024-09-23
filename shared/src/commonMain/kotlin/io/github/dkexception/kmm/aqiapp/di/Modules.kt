package io.github.dkexception.kmm.aqiapp.di

import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.data.preferences.PreferencesHelper
import io.github.dkexception.kmm.aqiapp.features.appcontent.AppContentViewModel
import io.github.dkexception.kmm.aqiapp.features.appcontent.IAppContentViewModel
import io.github.dkexception.kmm.aqiapp.features.auth.login.ILoginViewModel
import io.github.dkexception.kmm.aqiapp.features.auth.login.LoginViewModel
import io.github.dkexception.kmm.aqiapp.features.databank.DataBankMainViewModel
import io.github.dkexception.kmm.aqiapp.features.databank.IDataBankMainViewModel
import io.github.dkexception.kmm.aqiapp.features.details.AQIDetailsViewModel
import io.github.dkexception.kmm.aqiapp.features.details.IAQIDetailsViewModel
import io.github.dkexception.kmm.aqiapp.features.home.HomeViewModel
import io.github.dkexception.kmm.aqiapp.features.home.IHomeViewModel
import io.github.dkexception.kmm.aqiapp.features.more.IMoreListViewModel
import io.github.dkexception.kmm.aqiapp.features.more.MoreListViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.IGuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.welcome.WelcomeViewModel
import io.github.dkexception.kmm.aqiapp.features.scale.AQIScaleViewModel
import io.github.dkexception.kmm.aqiapp.features.scale.IAQIScaleViewModel
import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import io.github.dkexception.kmm.aqiapp.snackbar.SnackbarHelper
import io.github.dkexception.kmm.aqiapp.usecases.InitialAppNavigation
import io.github.dkexception.kmm.aqiapp.validators.ISingleStringValidator
import io.github.dkexception.kmm.aqiapp.validators.LoginEmailValidator
import io.github.dkexception.kmm.aqiapp.validators.LoginPasswordValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    factory {
        CoroutineScope(Dispatchers.Default)
    }

    singleOf(::PreferencesHelper).bind<IPreferencesHelper>()

    singleOf(::SnackbarHelper).bind<ISnackbarHelper>()

    singleOf(::InitialAppNavigation)

    single<ISingleStringValidator>(named("email")) {
        LoginEmailValidator()
    }

    single<ISingleStringValidator>(named("password")) {
        LoginPasswordValidator()
    }

    viewModelOf(::AppContentViewModel).bind<IAppContentViewModel>()

    viewModelOf(::WelcomeViewModel)
    viewModelOf(::GuideViewModel).bind<IGuideViewModel>()

    viewModel {

        val email = get<ISingleStringValidator>(named("email"))
        val pass = get<ISingleStringValidator>(named("password"))

        LoginViewModel(
            preferencesHelper = get(),
            emailValidator = email,
            passwordValidator = pass
        )
    }.bind<ILoginViewModel>()

    viewModelOf(::HomeViewModel).bind<IHomeViewModel>()

    viewModel {

        val email = get<ISingleStringValidator>(named("email"))

        DataBankMainViewModel(
            emailValidator = email
        )
    }.bind<IDataBankMainViewModel>()

    viewModelOf(::AQIDetailsViewModel).bind<IAQIDetailsViewModel>()
    viewModelOf(::AQIScaleViewModel).bind<IAQIScaleViewModel>()

    viewModelOf(::MoreListViewModel).bind<IMoreListViewModel>()
}
