package io.github.dkexception.kmm.aqiapp

interface Platform {

    val name: String

    fun getRandomUUID(): String

    fun printLog(message: String)

    fun openURLExternally(url: String)
}

expect fun getPlatform(): Platform
