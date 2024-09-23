package io.github.dkexception.kmm.aqiapp.features.appcontent

import io.github.dkexception.kmm.aqiapp.base.BaseScreenEvent
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import io.github.dkexception.kmm.aqiapp.usecases.InitialAppNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppContentViewModel(
    private val initialAppNavigation: InitialAppNavigation
) : BaseViewModel<BaseScreenEvent>(), IAppContentViewModel {

    private val _state = MutableStateFlow(AppContentScreenState())
    override val state: KMMStateFlow<AppContentScreenState> get() = _state.asStateFlow().common()

    init {
        navigateNext()
    }

    private fun navigateNext() = mScope.launch {
        _state.update {
            it.copy(
                iOSShouldShowWelcomeScreen = false,
                postWelcomeDestination = initialAppNavigation()
            )
        }
    }
}
