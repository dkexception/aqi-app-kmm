package io.github.dkexception.kmm.aqiapp.validators

import io.github.dkexception.kmm.aqiapp.utils.UIText

interface ISingleStringValidator {

    operator fun invoke(dataToValidate: String, allowBlank: Boolean = false): UIText?
}
