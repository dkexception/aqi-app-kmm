package io.github.dkexception.kmm.aqiapp.validators

import io.github.dkexception.kmm.aqiapp.utils.UIText
import io.github.dkexception.kmm.aqiapp.utils.toUIText

internal class LoginEmailValidator : ISingleStringValidator {

    private val emailPattern = Regex(
        "^[A-Za-z0-9._+\\-']+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$"
    )

    override operator fun invoke(dataToValidate: String, allowBlank: Boolean): UIText? {

        if (allowBlank && dataToValidate.isBlank()) {
            return null
        } else if (dataToValidate.isBlank()) {
            return "Email cannot be blank!".toUIText()
        }

        if (!emailPattern.matches(dataToValidate)) {
            return "Please enter a valid email!".toUIText()
        }

        return null
    }
}
