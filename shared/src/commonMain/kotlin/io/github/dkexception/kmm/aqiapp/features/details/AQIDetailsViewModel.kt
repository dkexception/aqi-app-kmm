package io.github.dkexception.kmm.aqiapp.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AQIDetailsViewModel(
    savedStateHandle: SavedStateHandle,
//    private val manager: IAirVisualDataManager
) : BaseViewModel<AQIDetailsEvent>(), IAQIDetailsViewModel {

    private val shouldUseIPLocation: Boolean = savedStateHandle["shouldUseIPLocation"] ?: false

    private val _state = MutableStateFlow(AQIDetailsScreenState())
    override val state: KMMStateFlow<AQIDetailsScreenState> get() = _state.asStateFlow().common()

    init {
        if (shouldUseIPLocation) {
            observeIPLocationData()
        }
    }

    override fun onEvent(aqiDetailsEvent: AQIDetailsEvent) = when (aqiDetailsEvent) {
        AQIDetailsEvent.OnPulledToRefresh -> Unit
    }

    private fun observeIPLocationData() = viewModelScope.launch {
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
