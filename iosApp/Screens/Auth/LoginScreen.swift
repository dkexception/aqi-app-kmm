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
        
        VStack {
            
            AQITextField(
                currentValue: loginViewModelAdapter.state.enteredEmailId,
                isError: loginViewModelAdapter.state.emailIdError != nil,
                onTextChange: { email in
                    loginViewModelAdapter.viewModel.onEvent(
                        loginEvent: LoginEvent.OnEmailChanged(newEmailId: email)
                    )
                }
            )
        }
        
        Button("Login Next") {
            loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnLoginClicked())
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct AQITextField: View {
    
    let currentValue: String
    
    let isError: Bool
    
    let onTextChange: (String) -> Void
    
//    @Binding var parentBinding: String
    
    var body: some View {
        
        let currentText = Binding<String>(
            get: {
                currentValue
            },
            set: {
                self.onTextChange($0)
            }
        )
        
        TextField("Type something here...", text: currentText)
            .padding(.horizontal)
            .frame(height: 55)
            .background(isError ? .red : .white)
            .clipShape(RoundedRectangle(cornerSize: CGSize(width: 10, height: 10)))
    }
}

#Preview {
    LoginScreen()
}
