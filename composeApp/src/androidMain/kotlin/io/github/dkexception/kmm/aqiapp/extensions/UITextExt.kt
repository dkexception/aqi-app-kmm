package io.github.dkexception.kmm.aqiapp.extensions

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.dkexception.kmm.aqiapp.R
import io.github.dkexception.kmm.aqiapp.utils.UIText

fun UIText.asString(context: Context): String {
    return when (this) {
        is UIText.DynamicString -> value
        UIText.NoInternet -> context.getString(R.string.no_internet)
    }
}

@Composable
fun UIText.asString(): String {
    return when (this) {
        is UIText.DynamicString -> value
        UIText.NoInternet -> stringResource(R.string.no_internet)
    }
}
