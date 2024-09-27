//
//  ButtonStyles.swift
//  iosApp
//
//  Created by Mangesh Murhe on 24/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI



struct FilledButton: ButtonStyle {
    @Environment (\.isEnabled) var isEnabled
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding(.all, 16)
            .frame(maxWidth: .infinity)
            .font(.bodyF)
            .foregroundColor(isEnabled ? .white : .white.opacity(0.5))
            .background(RoundedRectangle(cornerRadius: 10).fill(isEnabled ? .blue : .blue.opacity(0.5)))
            .cornerRadius(8)
    }
}

struct BorderelessButton: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding()
            .font(.bodyF)
            .foregroundColor(Color(red: 0.10, green: 0.10, blue: 0.12))
    }
}

struct CheckboxStyle: ToggleStyle {

    func makeBody(configuration: Self.Configuration) -> some View {

        return HStack {
            Image(systemName: configuration.isOn ? "checkmark.square" : "square")
                .resizable()
                .frame(width: 24, height: 24)
                .foregroundColor(configuration.isOn ? .blue : .gray)
                .font(.system(size: 20, weight: .regular, design: .default))
                configuration.label
        }
        .onTapGesture { configuration.isOn.toggle() }
    }
}
