package io.github.dkexception.ui.maps.initializer

import android.content.Context
import com.mappls.sdk.maps.Mappls
import com.mappls.sdk.services.account.MapplsAccountManager
import io.github.dkexception.ui.BuildConfig

class MapsInitializer {

    fun initialiseAQIMaps(applicationContext: Context) {

        initMapMyIndia(applicationContext = applicationContext)
    }

    private fun initMapMyIndia(applicationContext: Context) {

        MapplsAccountManager.getInstance().restAPIKey = BuildConfig.mapMyIndiaKey
        MapplsAccountManager.getInstance().mapSDKKey = BuildConfig.mapMyIndiaKey
        MapplsAccountManager.getInstance().atlasClientId = BuildConfig.mapMyIndiaClientId
        MapplsAccountManager.getInstance().atlasClientSecret = BuildConfig.mapMyIndiaClientSecret
        Mappls.getInstance(applicationContext)
    }
}
