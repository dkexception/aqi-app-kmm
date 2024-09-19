package io.github.dkexception.kmm.aqiapp.features.more

import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import io.github.dkexception.kmm.aqiapp.getPlatform
import io.github.dkexception.kmm.aqiapp.navigation.AQIDetailsRoutes
import io.github.dkexception.kmm.aqiapp.navigation.OnboardingRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MoreListViewModel(
//    private val dataStore: DataStore
) : BaseViewModel<MoreListEvent>(), IMoreListViewModel {

//    private val authUserData: AuthUserData? = try {
//
//        val userDataContent: String? = dataStore.getString(
//            key = Constants.SP_KEY_USER_DATA
//        )
//
//        userDataContent?.let {
//            Gson().fromJson(it, AuthUserData::class.java)
//        }
//    } catch (e: Exception) {
//        null
//    }

    private val _state = MutableStateFlow(
        MoreListScreenState(
            userName = /*authUserData?.name.takeUnless { it.isNullOrBlank() } ?:*/ "User",
            emailId = "authUserData?.emailId.orEmpty()",
            appName = getPlatform().appName,
            appVersion = getPlatform().version
        )
    )
    override val state: KMMStateFlow<MoreListScreenState> get() = _state.asStateFlow().common()

    override fun onEvent(event: MoreListEvent) {
        when (event) {
            is MoreListEvent.OnItemClicked -> {
                when (event.item) {
                    MoreListItem.PROFILE -> {

                    }

                    MoreListItem.SAVED_LOCATIONS -> {

                    }

                    MoreListItem.FAQ -> {
                        navigator.navigateWithObject(AQIDetailsRoutes.AQIScale)
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

            is MoreListEvent.OnConfirmLogoutPopupAction -> {

                _state.update {
                    it.copy(
                        isConfirmLogoutPopupVisible = false
                    )
                }

                if (event.isConfirm) {
//                    dataStore.nuke()
                    navigator.navigateClearingStackWithObject(OnboardingRoutes.OnboardingWelcome)
                }
            }
        }
    }
}
