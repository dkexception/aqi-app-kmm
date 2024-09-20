package io.github.dkexception.kmm.aqiapp.features.details

import io.github.dkexception.kmm.aqiapp.domain.models.aqi.AirQualityData

data class AQIDetailsScreenState(

    val isLoading: Boolean = false,

    val aqiData: AirQualityData = AirQualityData()
)
