package io.github.dkexception.kmm.aqiapp

import platform.Foundation.NSBundle
import platform.Foundation.NSDate
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
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
}

actual fun getPlatform(): Platform = IOSPlatform()
