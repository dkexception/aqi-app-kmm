package io.github.dkexception.kmm.aqiapp.maps.providers

enum class MapProvider {

    GOOGLE,

    OPEN_STREET_MAP,

    MAP_MY_INDIA;

    companion object {

        fun getDefault() = OPEN_STREET_MAP
    }
}
