package io.github.dkexception.kmm.aqiapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.navigation.Navigator
import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<Event> : ViewModel(), KoinComponent {

    protected val navigator: Navigator by inject()

    protected val snackbarHelper: ISnackbarHelper by inject()

    private val coroutineScope: CoroutineScope by inject()
    protected val mScope: CoroutineScope = if (navigator.isAndroid) {
        viewModelScope
    } else {
        coroutineScope
    }
}
