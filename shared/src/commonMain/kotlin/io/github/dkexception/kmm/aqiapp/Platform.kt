package io.github.dkexception.kmm.aqiapp

interface Platform {

    val appName: String

    val version: String

    fun getRandomUUID(): String

    fun printLog(message: String)

    fun openURLExternally(url: String)

    fun getSystemCurrentTimeMs(): String
}

expect fun getPlatform(): Platform
