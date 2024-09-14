package io.github.dkexception.kmm.aqiapp

import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice

class IOSPlatform : Platform {

    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    override fun getRandomUUID(): String = NSUUID().UUIDString()

    override fun printLog(message: String) = Unit

    override fun openURLExternally(url: String) {
        NSURL.URLWithString(url)?.let { UIApplication.sharedApplication.openURL(it) }
    }
}

actual fun getPlatform(): Platform = IOSPlatform()
