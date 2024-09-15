package io.github.dkexception.kmm.aqiapp.validators

import io.github.dkexception.kmm.aqiapp.utils.UIText
import io.github.dkexception.kmm.aqiapp.utils.toUIText

internal class LoginPasswordValidator : ISingleStringValidator {

    override operator fun invoke(dataToValidate: String, allowBlank: Boolean): UIText? {

        if (allowBlank && dataToValidate.isBlank()) {
            return null
        } else if (dataToValidate.isBlank()) {
            return "Password cannot be blank!".toUIText()
        }

        if (dataToValidate.length < 8) {
            return "Password is too short!".toUIText()
        }

        if (dataToValidate.all { it.isLetterOrDigit() }) {
            return "Password is too simple!".toUIText()
        }

        return null
    }
}
