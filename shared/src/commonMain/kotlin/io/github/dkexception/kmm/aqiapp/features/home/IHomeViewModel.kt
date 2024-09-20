package io.github.dkexception.kmm.aqiapp.features.home

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface IHomeViewModel {

    val state: KMMStateFlow<HomeScreenState>

    fun onEvent(homeEvent: HomeEvent)
}
