package io.github.dkexception.ui.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun LifecycleObserver.ComposableLifecycleListener() {

    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {

        lifecycle.addObserver(this@ComposableLifecycleListener)

        onDispose {
            lifecycle.removeObserver(this@ComposableLifecycleListener)
        }
    }
}
