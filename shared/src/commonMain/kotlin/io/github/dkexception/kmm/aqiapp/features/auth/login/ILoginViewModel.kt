package io.github.dkexception.kmm.aqiapp.features.auth.login

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface ILoginViewModel {

    val state: KMMStateFlow<LoginScreenState>

    fun onEvent(loginEvent: LoginEvent)
}
