package io.github.dkexception.kmm.aqiapp.features.home

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.data.preferences.AQIPreferencesKey
import io.github.dkexception.kmm.aqiapp.data.preferences.IPreferencesHelper
import io.github.dkexception.kmm.aqiapp.domain.models.profile.AuthUserData
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import io.github.dkexception.kmm.aqiapp.navigation.AQIDetailsRoutes
import io.github.dkexception.kmm.aqiapp.navigation.MoreRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val preferencesHelper: IPreferencesHelper
//    val aqiIAQIDetailsCard: IAQIDetailsCard,
//    private val manager: IAirVisualDataManager
) : BaseViewModel<HomeEvent>(), IHomeViewModel {

    private val _state = MutableStateFlow(HomeScreenState())
    override val state: KMMStateFlow<HomeScreenState> get() = _state.asStateFlow().common()

    init {
        loadUserData()
        setUpSDK()
    }

    override fun onEvent(homeEvent: HomeEvent) {
        when (homeEvent) {
            HomeEvent.OnPulledToRefresh -> Unit //manager.getDataByIPLocation()
            HomeEvent.OnSettingsClicked -> navigator.navigate(MoreRoutes.MoreList)
            HomeEvent.OnAQICardClicked -> {
                navigator.navigate(AQIDetailsRoutes.AQIDetails)
//                navigator.navigate(
//                    "${NavRoute.DETAILS.AQI_DETAILS}?shouldUseIPLocation=${state.value.aqiData?.isFromIPLocation ?: true}"
//                )
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
                state.copy(userName = userData.name)
            }
        }
    }

    private fun setUpSDK() = viewModelScope.launch {

//        manager.initialise()
//        manager.getDataByIPLocation()
//        manager.ipLocationData.collect { (isLoading, aqiData) ->
//
//            _state.update {
//                it.copy(
//                    isLoading = isLoading,
//                    aqiData = aqiData
//                )
//            }
//        }
    }
}
