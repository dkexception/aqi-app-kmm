package io.github.dkexception.kmm.aqiapp.features.auth.login

import io.github.dkexception.kmm.aqiapp.utils.UIText

data class LoginScreenState(

    val enteredName: String = "",

    val enteredEmailId: String = "",
    val emailIdError: UIText? = null,

    val enteredPassword: String = "",
    val passwordError: UIText? = null,

    val isLoginButtonEnabled: Boolean = false
)
