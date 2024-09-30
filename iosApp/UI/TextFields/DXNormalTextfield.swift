//
//  DXNormalTextfield.swift
//  iosApp
//
//  Created by A200072570 on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

protocol DXTextFieldProtocol {
    var iconName: String? { get }
    var title: String { get }
    var currentValue: String { get }
    var errorText: String? { get }
    var onTextChanged: (String)-> Void {get}
}

struct DXNormalTextField: View, DXTextFieldProtocol {
    var iconName: String?
    
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

#Preview {
    DXNormalTextField(title: "Your Name", currentValue: "", onTextChanged: {_ in })
}
