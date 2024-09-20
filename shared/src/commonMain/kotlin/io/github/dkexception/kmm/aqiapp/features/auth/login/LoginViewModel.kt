package io.github.dkexception.kmm.aqiapp.features.auth.login

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.data.preferences.AQIPreferencesKey
import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.domain.models.profile.AuthUserData
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import io.github.dkexception.kmm.aqiapp.navigation.HomeRoutes
import io.github.dkexception.kmm.aqiapp.utils.UIText
import io.github.dkexception.kmm.aqiapp.validators.ISingleStringValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferencesHelper: IPreferencesHelper,
    private val emailValidator: ISingleStringValidator,
    private val passwordValidator: ISingleStringValidator
) : BaseViewModel<LoginEvent>(), ILoginViewModel {

    private val _state = MutableStateFlow(LoginScreenState())
    override val state: KMMStateFlow<LoginScreenState>
        get() = _state.map {

            val uiEmailError: UIText? = emailValidator(it.enteredEmailId, true)

            val uiPasswordError: UIText? = passwordValidator(it.enteredPassword, true)

            val shouldEnableLoginButton: Boolean = uiEmailError == null
                    && uiPasswordError == null
                    && it.enteredEmailId.isNotBlank()
                    && it.enteredPassword.isNotBlank()
                    && it.enteredName.isNotBlank()

            it.copy(
                isLoginButtonEnabled = shouldEnableLoginButton,
                emailIdError = uiEmailError,
                passwordError = uiPasswordError
            )
        }.stateIn(mScope, SharingStarted.WhileSubscribed(), LoginScreenState()).common()

    override fun onEvent(loginEvent: LoginEvent) {
        when (loginEvent) {

            is LoginEvent.OnNameChanged -> {
                _state.update {
                    it.copy(
                        enteredName = loginEvent.newName
                    )
                }
            }

            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        enteredEmailId = loginEvent.newEmailId
                    )
                }
            }

            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        enteredPassword = loginEvent.newPassword
                    )
                }
            }

            LoginEvent.OnLoginClicked -> {

                viewModelScope.launch {

                    // Set user logged in by saving the auth data
                    preferencesHelper.putString(
                        key = AQIPreferencesKey.SP_KEY_USER_DATA,
                        value = AuthUserData(
                            name = _state.value.enteredName,
                            emailId = _state.value.enteredEmailId.lowercase()
                        ).encodeToString()
                    )

                    // And navigate to the dashboard
                    navigator.navigateClearingStackWithObject(HomeRoutes.HomeMain)
                }
            }
        }
    }
}
