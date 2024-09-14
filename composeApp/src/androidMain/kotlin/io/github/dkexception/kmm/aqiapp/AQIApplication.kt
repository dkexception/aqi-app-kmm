package io.github.dkexception.kmm.aqiapp

import android.app.Application
import io.github.dkexception.kmm.aqiapp.di.initKoin
import io.github.dkexception.kmm.aqiapp.di.mapsModule
import io.github.dkexception.kmm.aqiapp.maps.initializer.MapsInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AQIApplication : Application(), KoinComponent {

    private val mapsInitializer: MapsInitializer by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AQIApplication)
            modules(mapsModule)
        }
        mapsInitializer.initialiseAQIMaps(this)
    }
}
