@file:OptIn(ExperimentalForeignApi::class)

package io.github.dkexception.kmm.aqiapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.github.dkexception.kmm.aqiapp.data.preferences.createDataStore
import io.github.dkexception.kmm.aqiapp.data.preferences.dataStoreFileName
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSBundle
import platform.Foundation.NSDate
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.Foundation.timeIntervalSinceReferenceDate
import platform.UIKit.UIApplication

class IOSPlatform : Platform {

    override val appName: String = "AQI App"

    override val version: String =
        NSBundle.mainBundle().objectForInfoDictionaryKey("CFBundleShortVersionString").toString()

    override fun getRandomUUID(): String = NSUUID().UUIDString()

    override fun printLog(message: String) = Unit

    override fun openURLExternally(url: String) {
        NSURL.URLWithString(url)?.let { UIApplication.sharedApplication.openURL(it) }
    }

    override fun getSystemCurrentTimeMs(): String = NSDate.timeIntervalSinceReferenceDate.toString()

    override fun getPrefsDataStore(): DataStore<Preferences> = createDataStore(
        producePath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    )
}

actual fun getPlatform(): Platform = IOSPlatform()
