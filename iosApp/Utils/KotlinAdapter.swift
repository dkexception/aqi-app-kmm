//
//  KotlinAdapter.swift
//  iosApp
//
//  Created by Dhanesh Katre on 14/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class KotlinAdapter<ViewModelType, StateType: AnyObject>: ObservableObject {
    
    let viewModel: ViewModelType
    
    @Published var state: StateType
    
    private let viewScope: Kotlinx_coroutines_coreCoroutineScope = IOSHelpers().coroutineScope
    
    private var observingTask: Task<Void, Never>?
    
    private var onEachAction: ((StateType) -> Void)? = nil
    
    func setOnEachAction(action: @escaping (StateType) -> Void) {
        self.onEachAction = action
    }
    
    // While going generic, we need to know how to retrieve the state from
    // the view-model. So we take a view-model factory and a state getter
    // as init params.
    init(
        createViewModel: (Kotlinx_coroutines_coreCoroutineScope) -> ViewModelType,
        getState: (ViewModelType) -> KMMStateFlow<StateType>
    ) {
        let viewModel = createViewModel(viewScope)
        let stateFlow = getState(viewModel)
        self.viewModel = viewModel
        self.state = stateFlow.value!
        observingTask = Task { @MainActor [weak self] in
            await collect(stateFlow) { self?.state = $0 }
        }
    }
    
    init(
        initialValue: StateType,
        createViewModel: (Kotlinx_coroutines_coreCoroutineScope) -> ViewModelType,
        getState: (ViewModelType) -> KMMFlow<StateType>
    ) {
        
        let viewModel = createViewModel(viewScope)
        let stateFlow = getState(viewModel)
        self.viewModel = viewModel
        self.state = initialValue
        observingTask = Task { @MainActor [weak self] in
            await collect(stateFlow) {
                guard let self else {
                    return
                }
                self.state = $0
                self.onEachAction?($0)
            }
        }
    }
    
    deinit {
        // This will cancel the underlying coroutine as well as the coroutine
        // scope of the view when the view is destroyed.
        observingTask?.cancel()
        IOSHelpers().cancel(coroutineScope: viewScope)
    }
}
