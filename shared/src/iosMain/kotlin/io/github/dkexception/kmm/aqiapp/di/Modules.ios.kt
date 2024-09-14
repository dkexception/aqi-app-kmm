package io.github.dkexception.kmm.aqiapp.di

import io.github.dkexception.kmm.aqiapp.navigation.IOSNavigator
import io.github.dkexception.kmm.aqiapp.navigation.Navigator
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {

    singleOf(::IOSNavigator).bind<Navigator>()
}
