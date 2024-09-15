package io.github.dkexception.kmm.aqiapp.utils

sealed class UIText {

    companion object {

        val EMPTY = DynamicString("")

        val DEFAULT_ERROR_TEXT = DynamicString("Error")
    }

    data class DynamicString(val value: String) : UIText()

    data object NoInternet : UIText()
}

fun String.toUIText(): UIText = UIText.DynamicString(this)
