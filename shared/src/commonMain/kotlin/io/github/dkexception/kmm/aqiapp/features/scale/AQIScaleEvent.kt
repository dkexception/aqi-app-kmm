package io.github.dkexception.kmm.aqiapp.features.scale

sealed class AQIScaleEvent {

    data object OnBackClicked : AQIScaleEvent()
}
