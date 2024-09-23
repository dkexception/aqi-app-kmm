package io.github.dkexception.kmm.aqiapp.features.appcontent

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface IAppContentViewModel {

    val state: KMMStateFlow<AppContentScreenState>
}
