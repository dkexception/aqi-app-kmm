//
//  DXPasswordTextField.swift
//  iosApp
//
//  Created by A200072570 on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DXPasswordTextField: View, DXTextFieldProtocol {
    var iconName: String?
    
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
    DXPasswordTextField(title: "Your Name", currentValue: "", onTextChanged: {_ in 
        
    })
}
