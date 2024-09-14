package io.github.dkexception.kmm.aqiapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

class AndroidPlatform : Platform, KoinComponent {

    private val context: Context by inject()

    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    override fun getRandomUUID(): String = UUID.randomUUID().toString()

    override fun printLog(message: String) {
        Log.d("AQIAppKMM", message)
    }

    override fun openURLExternally(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()
