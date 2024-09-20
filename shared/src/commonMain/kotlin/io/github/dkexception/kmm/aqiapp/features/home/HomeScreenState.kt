package io.github.dkexception.kmm.aqiapp.features.home

import io.github.dkexception.kmm.aqiapp.domain.models.aqi.AirQualityData

data class HomeScreenState(

    val isLoading: Boolean = false,

    val userName: String = "User",

    val aqiData: AirQualityData? = null
)
