package io.github.dkexception.kmm.aqiapp.di

import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import io.github.dkexception.kmm.aqiapp.snackbar.SnackbarHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    factory {
        CoroutineScope(Dispatchers.Default)
    }

    singleOf(::SnackbarHelper).bind<ISnackbarHelper>()

//    viewModelOf(::ScreenViewModel).bind<IScreenViewModel>()
}
