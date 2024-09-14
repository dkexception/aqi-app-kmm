package io.github.dkexception.ui.di

import io.github.dkexception.ui.maps.IMapView
import io.github.dkexception.ui.maps.ProviderAwareMapView
import io.github.dkexception.ui.maps.initializer.MapsInitializer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mapsModule = module {

    factory {
        ProviderAwareMapView()
    }.bind<IMapView>()

    singleOf(::MapsInitializer)
}
