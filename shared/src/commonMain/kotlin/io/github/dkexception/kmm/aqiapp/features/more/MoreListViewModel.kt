package io.github.dkexception.kmm.aqiapp.features.more

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.data.preferences.AQIPreferencesKey
import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.domain.models.profile.AuthUserData
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import io.github.dkexception.kmm.aqiapp.getPlatform
import io.github.dkexception.kmm.aqiapp.navigation.AQIDetailsRoutes
import io.github.dkexception.kmm.aqiapp.navigation.OnboardingRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoreListViewModel(
    private val preferencesHelper: IPreferencesHelper
) : BaseViewModel<MoreListEvent>(), IMoreListViewModel {

    init {
        loadUserData()
    }

    private val _state = MutableStateFlow(
        MoreListScreenState(
            appName = getPlatform().appName,
            appVersion = getPlatform().version
        )
    )
    override val state: KMMStateFlow<MoreListScreenState> get() = _state.asStateFlow().common()

    override fun onEvent(moreListEvent: MoreListEvent) {
        when (moreListEvent) {
            is MoreListEvent.OnItemClicked -> {
                when (moreListEvent.item) {
                    MoreListItem.PROFILE -> {

                    }

                    MoreListItem.SAVED_LOCATIONS -> {

                    }

                    MoreListItem.FAQ -> {
                        navigator.navigate(AQIDetailsRoutes.AQIScale)
                    }

                    MoreListItem.SETTINGS -> {

                    }

                    MoreListItem.ABOUT_US -> {

                    }

                    MoreListItem.CONTACT_US -> {

                    }

                    MoreListItem.LOGOUT -> {
                        _state.update {
                            it.copy(
                                isConfirmLogoutPopupVisible = true
                            )
                        }
                    }
                }
            }

            is MoreListEvent.OnConfirmLogoutPopupAction -> viewModelScope.launch {

                _state.update {
                    it.copy(
                        isConfirmLogoutPopupVisible = false
                    )
                }

                if (moreListEvent.isConfirm) {
                    preferencesHelper.nuke()
                    navigator.navigateClearingStack(OnboardingRoutes.OnboardingWelcome)
                }
            }
        }
    }

    private fun loadUserData() = viewModelScope.launch {

        val userDataContent: String? = preferencesHelper.getString(
            key = AQIPreferencesKey.SP_KEY_USER_DATA
        )

        userDataContent?.let {

            val userData: AuthUserData = AuthUserData.decodeFromString(it)

            _state.update { state ->
                state.copy(
                    userName = userData.name,
                    emailId = userData.emailId
                )
            }
        }
    }
}
