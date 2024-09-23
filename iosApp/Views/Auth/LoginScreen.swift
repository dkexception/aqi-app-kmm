//
//  LoginScreen.swift
//  iosApp
//
//  Created by Dhanesh Katre on 23/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct LoginScreen: View {
    
    @StateObject
    var loginViewModelAdapter = KotlinAdapter<ILoginViewModel, LoginScreenState>(
        createViewModel: { _ in
            IOSHelpers().provideLoginViewModel()
        },
        getState: { $0.state }
    )
    
    var body: some View {
        
        Button("Login Next") {
            loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnLoginClicked())
        }
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    LoginScreen()
}
