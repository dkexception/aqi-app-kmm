package io.github.dkexception.kmm.aqiapp.features.details

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface IAQIDetailsViewModel {

    val state: KMMStateFlow<AQIDetailsScreenState>

    fun onEvent(aqiDetailsEvent: AQIDetailsEvent)
}
