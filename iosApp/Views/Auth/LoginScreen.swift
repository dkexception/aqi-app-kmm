//
//  LoginScreen.swift
//  iosApp
//
//  Created by Mangesh Murhe on 23/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

enum TextFieldType {
    case normalTextField
    case passwordTextField
}

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
                                 type:.normalTextField,
                                 title: "Your Email Address",
                                 currentValue: loginViewModelAdapter.state.enteredEmailId,
                                 errorText: (loginViewModelAdapter.state.emailIdError as? UIText.DynamicString)?.value ?? "", onTextChanged: { text in
                        loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnEmailChanged(newEmailId: text))
                    }).padding(.top, 24)
                    
                    DXPasswordTextField(iconName: "password_icon",
                                 type:.passwordTextField,
                                 title: "Your Password", currentValue: loginViewModelAdapter.state.enteredPassword,
                                 errorText: (loginViewModelAdapter.state.passwordError as? UIText.DynamicString)?.value ?? "",
                                  onTextChanged: { text in
                        loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnPasswordChanged(newPassword: text))
                    }).padding(.top, 8)
                    
                    HStack {
                        Toggle(isOn: $rememberMe) {
                            Text("Remember me")
                                .font(.body1F)
                        }
                        .toggleStyle(CheckboxStyle())
                        Spacer()
                        Button("Forgot password?") {
                            
                        }.buttonStyle(BorderelessButton())
                    }.padding(.horizontal, 16)
                }
                Spacer()
            }
            
            Spacer()
        }.onAppear(){
            loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnNameChanged(newName: "Mangesh"))
        }
        
        Button("Login Next") {
            loginViewModelAdapter.viewModel.onEvent(loginEvent: LoginEvent.OnLoginClicked())
        }
        .buttonStyle(FilledButton())
        .padding(.horizontal, 16)
        .disabled(!loginViewModelAdapter.state.isLoginButtonEnabled)
        .navigationBarBackButtonHidden(true)
    }
}

protocol DXTextFieldProtocol {
    var iconName: String? { get }
    var type: TextFieldType { get }
    var title: String { get }
    var currentValue: String { get }
    var errorText: String? { get }
    var onTextChanged: (String)-> Void {get}
}

struct DXNormalTextField: View, DXTextFieldProtocol {
    var iconName: String?
    
    var type: TextFieldType
    
    var title: String
    
    var currentValue: String
    
    var errorText: String?
    
    var onTextChanged: (String) -> Void

    var body: some View {
      let currentText = Binding<String>(
        get:{
            currentValue
        },
        set:{
            onTextChanged($0)
        })
        VStack {
            HStack {
                if let iconName = iconName {
                    Image(iconName)
                        .frame(width: 24, height: 24)
                        .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                }
              
                TextField(title, text: currentText)
                    
            }.padding()
                .overlay(RoundedRectangle(cornerRadius: 10).stroke(lineWidth: 2).foregroundColor(Color.black))
                .padding(.horizontal, 16)
            if let text = errorText {
                HStack() {
                    Text(text)
                        .font(.body1F)
                        .foregroundStyle(.red)
                        .padding(.leading,32)
                    Spacer()
                }
            }
        }
    }
}

struct DXPasswordTextField: View, DXTextFieldProtocol {
    var iconName: String?
    
    var type: TextFieldType
    
    var title: String
    
    var currentValue: String
    
    var errorText: String?
    
    var onTextChanged: (String) -> Void
    
    @State var isSecure = false
    
    private enum Focus {
        case secure, plain
    }
    
    @FocusState
    private var inFocuse: Focus?
    
    var body: some View {
        let currentText = Binding<String>(
          get:{
              currentValue
          },
          set:{
              onTextChanged($0)
          })
        VStack {
            HStack {
                if let iconName = iconName {
                    Image(iconName)
                        .frame(width: 24, height: 24)
                        .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                }
                ZStack(alignment: .trailing) {
                    SecureField(title, text: currentText)
                        .focused($inFocuse, equals: .secure)
                        .padding(.leading, 8)
                        .opacity(isSecure ? 0: 1)
                    TextField(title, text: currentText)
                        .focused($inFocuse, equals: .plain)
                        .opacity(isSecure ? 1 : 0)
                        .padding(.leading, 8)
                    
                    HStack {
                        Spacer()
                        Button(action: {
                            isSecure.toggle()
                            inFocuse = isSecure ? .secure : .plain
                        }, label: {
                            Image(systemName: isSecure ? "eye" : "eye.slash")
                                .accentColor(.gray)
                        })
                    }
                }
            }.padding()
                .overlay(RoundedRectangle(cornerRadius: 10).stroke(lineWidth: 2).foregroundColor(Color.black))
                .padding(.horizontal, 16)
            if let text = errorText {
                HStack() {
                    Text(text)
                        .font(.body1F)
                        .foregroundStyle(.red)
                        .padding(.leading,32)
                    Spacer()
                }
            }
        }
    }
}

#Preview {
    LoginScreen()
}
