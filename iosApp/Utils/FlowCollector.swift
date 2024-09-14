//
//  FlowCollector.swift
//  iosApp
//
//  Created by Dhanesh Katre on 14/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

// We want to mimic StateFlow's collect method by having only a onEach lambda.
// It is async and its lifecycle is tied to the underlying coroutine
// of startCollect.
func collect<T>(
    _ stateFlow: KMMStateFlow<T>,
    onEach: @escaping (T) -> Void
) async {
    var collectionCancelled: CheckedContinuation<Void, Never>?
    let cancellable = stateFlow.startCollect(
        // Here we must still force unwrap because of Objective-C.
        onEach: { onEach($0!) },
        // In case of the coroutine cancellation, we resume the current execution
        // leading to the end of the method.
        onCancel: { collectionCancelled?.resume() }
    )
    await withTaskCancellationHandler {
        await withCheckedContinuation { continuation in
            // We store the continuation waiting either
            // for a cancellation from Kotlin that will resume it or for
            // a cancellation from Swift that will cancel this await statement.
            collectionCancelled = continuation
        }
    } onCancel: {
        // In case of Task cancellation, we take care of cancelling the Kotlin
        // job as well.
        cancellable.cancel()
    }
}

func collect<T>(
    _ flow: KMMFlow<T>,
    onEach: @escaping (T) -> Void
) async {
    var collectionCancelled: CheckedContinuation<Void, Never>?
    let cancellable = flow.startCollect(
        // Here we must still force unwrap because of Objective-C.
        onEach: { onEach($0!) },
        // In case of the coroutine cancellation, we resume the current execution
        // leading to the end of the method.
        onCancel: { collectionCancelled?.resume() }
    )
    await withTaskCancellationHandler {
        await withCheckedContinuation { continuation in
            // We store the continuation waiting either
            // for a cancellation from Kotlin that will resume it or for
            // a cancellation from Swift that will cancel this await statement.
            collectionCancelled = continuation
        }
    } onCancel: {
        // In case of Task cancellation, we take care of cancelling the Kotlin
        // job as well.
        cancellable.cancel()
    }
}
