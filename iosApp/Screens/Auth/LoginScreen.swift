//
//  LoginScreen.swift
//  iosApp
//
//  Created by Mangesh Murhe on 23/09/24.
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
    @State var emailText = ""
    @State var password = ""
    @State var isVisible = true
    @State var rememberMe = false
    
    var body: some View {
        
        let _ = print(loginViewModelAdapter.state.emailIdError ?? "")
        
        VStack {
            Image("login_screen_top_image")
            HStack() {
                VStack(alignment:.leading, spacing: 8) {
                    Text("Login")
                        .font(.titleF)
                        .padding(.leading, 16)
                    Text("Please login to get your local AQI data.")
                        .font(.bodyF)
                        .padding(.leading, 16)
                    
                    DXNormalTextField(iconName: "email_icon",
                                 title: "Your Email Address",
                                 currentValue: loginViewModelAdapter.state.enteredEmailId,
                                 errorText: (loginViewModelAdapter.state.emailIdError as? UIText.DynamicString)?.value ?? "", onTextChanged: { text in
                        loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnEmailChanged(newEmailId: text))
                    }).padding(.top, 24)
                    
                    DXPasswordTextField(iconName: "password_icon",
                                 title: "Your Password", currentValue: loginViewModelAdapter.state.enteredPassword,
                                 errorText: (loginViewModelAdapter.state.passwordError as? UIText.DynamicString)?.value ?? "",
                                  onTextChanged: { text in
                        loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnPasswordChanged(newPassword: text))
                    }).padding(.top, 8)
                    
//                    HStack {
//                        Toggle(isOn: $rememberMe) {
//                            Text("Remember me")
//                                .font(.body1F)
//                        }
//                        .toggleStyle(CheckboxStyle())
//                        Spacer()
//                        Button("Forgot password?") {
//                            
//                        }.buttonStyle(BorderelessButton())
//                    }.padding(.horizontal, 16)
                }
                Spacer()
            }
            
            Spacer()
        }.onAppear(){
            loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnNameChanged(newName: "Mangesh"))
        }
        
        DXPrimaryButton(buttonText: "Login Next", onClickAction: {
            loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnLoginClicked())
        }).padding(.horizontal, 16)
        .disabled(!loginViewModelAdapter.state.isLoginButtonEnabled)
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    LoginScreen()
}
