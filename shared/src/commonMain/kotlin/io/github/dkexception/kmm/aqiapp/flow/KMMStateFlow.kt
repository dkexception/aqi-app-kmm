package io.github.dkexception.kmm.aqiapp.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class KMMStateFlow<T>(
    private val underlyingStateFlow: StateFlow<T>
) : StateFlow<T> {

    override val replayCache: List<T>
        get() = underlyingStateFlow.replayCache

    override val value: T
        get() = underlyingStateFlow.value

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        underlyingStateFlow.collect(collector)
    }

    // This is basically a method that starts a coroutine that collects the flow.
    // onCancel is called to forward the coroutine cancellation to iOS and the
    // CommonCancellable enables to cancel this coroutine from iOS.
    fun startCollect(
        onEach: (T) -> Unit,
        onCancel: () -> Unit
    ): CommonCancellable {
        val collectionScope = CoroutineScope(Dispatchers.Main)
        val collectionJob: Job = collectionScope.launch {
            try {
                collect(collector = { onEach(it) })
            } catch (e: CancellationException) {
                onCancel()
                throw e
            }
        }
        return CommonCancellable(cancel = { collectionJob.cancel() })
    }
}

internal fun <T : Any> StateFlow<T>.common(): KMMStateFlow<T> = KMMStateFlow(this)
