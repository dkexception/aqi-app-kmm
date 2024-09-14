package io.github.dkexception.kmm.aqiapp.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class KMMFlow<T>(
    private val underlyingStateFlow: Flow<T>
) : Flow<T> {

    override suspend fun collect(collector: FlowCollector<T>) {
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

internal fun <T : Any> Flow<T>.common(): KMMFlow<T> = KMMFlow(this)
