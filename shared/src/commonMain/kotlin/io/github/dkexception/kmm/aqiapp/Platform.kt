package io.github.dkexception.kmm.aqiapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface Platform {

    val appName: String

    val version: String

    fun getRandomUUID(): String

    fun printLog(message: String)

    fun openURLExternally(url: String)

    fun getSystemCurrentTimeMs(): String

    fun getPrefsDataStore(): DataStore<Preferences>
}

expect fun getPlatform(): Platform
