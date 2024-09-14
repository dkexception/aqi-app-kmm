package io.github.dkexception.kmm.aqiapp.di

import io.github.dkexception.kmm.aqiapp.maps.IMapView
import io.github.dkexception.kmm.aqiapp.maps.ProviderAwareMapView
import io.github.dkexception.kmm.aqiapp.maps.initializer.MapsInitializer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mapsModule = module {

    factory {
        ProviderAwareMapView()
    }.bind<IMapView>()

    singleOf(::MapsInitializer)
}
