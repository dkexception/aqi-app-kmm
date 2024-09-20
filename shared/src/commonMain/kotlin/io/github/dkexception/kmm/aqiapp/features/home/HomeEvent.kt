package io.github.dkexception.kmm.aqiapp.features.home

sealed class HomeEvent {

    data object OnPulledToRefresh : HomeEvent()

    data object OnAQICardClicked : HomeEvent()

    data object OnSettingsClicked : HomeEvent()
}
