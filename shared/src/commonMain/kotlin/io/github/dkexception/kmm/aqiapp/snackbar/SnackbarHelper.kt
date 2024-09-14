package io.github.dkexception.kmm.aqiapp.snackbar

import io.github.dkexception.kmm.aqiapp.flow.KMMFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackbarEvent(
    val message: String,
    val action: SnackbarAction? = null
)

data class SnackbarAction(
    val name: String,
    val action: () -> Unit
)

interface ISnackbarHelper {

    val events: KMMFlow<SnackbarEvent>

    suspend fun sendEvent(event: SnackbarEvent)
}

internal class SnackbarHelper : ISnackbarHelper {

    private val _events = Channel<SnackbarEvent>()
    override val events = _events.receiveAsFlow().common()

    override suspend fun sendEvent(event: SnackbarEvent) {
        _events.send(event)
    }
}
