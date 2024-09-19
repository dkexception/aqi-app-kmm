package io.github.dkexception.kmm.aqiapp.features.scale

import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AQIScaleViewModel : BaseViewModel<AQIScaleEvent>(), IAQIScaleViewModel {

    private val _state = MutableStateFlow(AQIScaleScreenState())
    override val state: KMMStateFlow<AQIScaleScreenState> get() = _state.asStateFlow().common()

    override fun onEvent(aqiScaleEvent: AQIScaleEvent) {
        when (aqiScaleEvent) {
            AQIScaleEvent.OnBackClicked -> navigator.goBack()
        }
    }
}
