package io.github.dkexception.kmm.aqiapp.features.more

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface IMoreListViewModel {

    val state: KMMStateFlow<MoreListScreenState>

    fun onEvent(moreListEvent: MoreListEvent)
}
