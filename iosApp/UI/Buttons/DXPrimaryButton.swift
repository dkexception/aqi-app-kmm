//
//  DXPrimaryButton.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DXPrimaryButton: View {
    
    let buttonText: String
    
    let onClickAction: () -> Void
    
    @Environment(\.isEnabled) var isEnabled
    
    var body: some View {
        
        Button {
            onClickAction()
        } label: {
            Text(buttonText)
                .padding(.all, 16)
                .frame(maxWidth: .infinity)
                .font(
                    Font.system(size: 16, weight: .semibold)
                )
                .foregroundColor(
                    isEnabled ? .white : .white.opacity(0.5)
                )
                .background(
                    RoundedRectangle(cornerRadius: 10)
                        .fill(
                            isEnabled ? AQIColors.shared.primaryColor.common : AQIColors.shared.primaryColor.common.opacity(0.5)
                        )
                )
                .cornerRadius(8)
                .shadow(radius: 4.0)
        }
        .padding(.horizontal, 32)
    }
}

#Preview {
    DXPrimaryButton(
        buttonText: "Let's get started!",
        onClickAction: {}
    )
}
