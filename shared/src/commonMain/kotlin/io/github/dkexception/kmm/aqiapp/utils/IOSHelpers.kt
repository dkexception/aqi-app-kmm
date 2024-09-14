package io.github.dkexception.kmm.aqiapp.utils

import io.github.dkexception.kmm.aqiapp.navigation.Navigator
import io.github.dkexception.kmm.aqiapp.snackbar.ISnackbarHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IOSHelpers : KoinComponent {

    @Suppress("MemberVisibilityCanBePrivate") // Used in iOS
    val navigator: Navigator by inject()

    @Suppress("MemberVisibilityCanBePrivate") // Used in iOS
    val snackbarHelper: ISnackbarHelper by inject()

    val coroutineScope: CoroutineScope by inject()

    fun cancel(coroutineScope: CoroutineScope) {
        coroutineScope.cancel()
    }
}
