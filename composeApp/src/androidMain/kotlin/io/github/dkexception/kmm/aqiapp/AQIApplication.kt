package io.github.dkexception.kmm.aqiapp

import android.app.Application
import io.github.dkexception.kmm.aqiapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class AQIApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AQIApplication)
        }
    }
}
