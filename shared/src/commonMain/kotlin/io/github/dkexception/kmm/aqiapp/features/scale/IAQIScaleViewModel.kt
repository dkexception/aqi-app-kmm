package io.github.dkexception.kmm.aqiapp.features.scale

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface IAQIScaleViewModel {

    val state: KMMStateFlow<AQIScaleScreenState>

    fun onEvent(aqiScaleEvent: AQIScaleEvent)
}
