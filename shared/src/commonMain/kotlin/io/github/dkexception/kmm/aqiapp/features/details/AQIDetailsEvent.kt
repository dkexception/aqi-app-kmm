package io.github.dkexception.kmm.aqiapp.features.details

sealed class AQIDetailsEvent {

    data object OnPulledToRefresh : AQIDetailsEvent()
}
