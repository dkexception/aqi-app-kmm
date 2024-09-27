//
//  AppContent.swift
//  iosApp
//
//  Created by Dhanesh Katre on 14/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct AppContent: View {
    
    @ObservedObject
    var navigator: AQIAppNavigator = AQIAppNavigator()
    
    @State
    var isSnackbarVisible: Bool = false
    
    @StateObject
    var snackbarAdapter = KotlinAdapter<ISnackbarHelper, SnackbarEvent>(
        initialValue: SnackbarEvent(message: "", action: nil),
        createViewModel: { _ in
            IOSHelpers().snackbarHelper
        },
        getState: { $0.events }
    )
    
    @StateObject
    var appContentViewModelAdapter = KotlinAdapter<IAppContentViewModel, AppContentScreenState>(
        createViewModel: { _ in
            IOSHelpers().provideAppContentViewModel()
        },
        getState: { $0.state }
    )
    
    var body: some View {
        
        ZStack {
            
            if appContentViewModelAdapter.state.iOSShouldShowWelcomeScreen {
                
                WelcomeScreen()
            } else {
                
                NavigationStack(path: $navigator.path) {
                    
                    let postWelcomeDestination = appContentViewModelAdapter.state.postWelcomeDestination
                    
                    InitialScreen(
                        postWelcomeDestination: postWelcomeDestination
                    )
                    .navigationDestination(for: AuthRoutes.AuthLogin.self) { _ in
                        LoginScreen()
                    }
                    .navigationDestination(for: HomeRoutes.HomeMain.self) { _ in
                        HomeScreen()
                    }
                    .navigationDestination(for: MoreRoutes.MoreList.self) { _ in
                        MoreListScreen()
                    }
                    .navigationDestination(for: OtherRoutes.Invalid404.self) { _ in
                        Screen404()
                    }
                }
            }
            
            if isSnackbarVisible {
                
                let event = snackbarAdapter.state
                
                if !event.message.isEmpty || event.action != nil {
                    SnackbarView(
                        event: event,
                        isVisible: $isSnackbarVisible
                    )
                    .onAppear {
                        DispatchQueue.main.asyncAfter(deadline: .now() + 4.0) {
                            isSnackbarVisible = false
                        }
                    }
                }
            }
        }.onAppear {
            snackbarAdapter.setOnEachAction { event in
                self.isSnackbarVisible = false
                self.isSnackbarVisible = true
            }
        }
    }
}

private struct InitialScreen: View {
    
    let postWelcomeDestination: PostWelcomeDestination
    
    var body: some View {
        
        if postWelcomeDestination == PostWelcomeDestination.guide {
            return AnyView(GuideScreen())
        }
        
        if postWelcomeDestination == PostWelcomeDestination.login {
            return AnyView(LoginScreen())
        }
        
        if postWelcomeDestination == PostWelcomeDestination.home {
            return AnyView(HomeScreen())
        }
        
        return AnyView(Screen404())
    }
}

#Preview {
    AppContent()
}
