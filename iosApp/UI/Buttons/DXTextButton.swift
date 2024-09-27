//
//  DXTextButton.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DXTextButton: View {
    
    let buttonText: String
    
    let onClickAction: () -> Void
    
    let isPrimaryThemed: Bool
    
    private var primaryForegroundTheme: Color {
        isEnabled ? AQIColors.shared.primaryColor.common : AQIColors.shared.primaryColor.common.opacity(0.5)
    }
    
    private var normalForegroundTheme: Color {
        isEnabled ? .black : .black.opacity(0.5) // TODO: take from AQIColors
    }
    
    init(buttonText: String, onClickAction: @escaping () -> Void, isPrimaryThemed: Bool = false) {
        
        self.buttonText = buttonText
        self.onClickAction = onClickAction
        self.isPrimaryThemed = isPrimaryThemed
    }
    
    @Environment(\.isEnabled) var isEnabled
    
    var body: some View {
        
        Button {
            onClickAction()
        } label: {
            Text(buttonText)
                .font(
                    Font.system(size: 16, weight: .semibold)
                )
                .foregroundColor(
                    isPrimaryThemed ? primaryForegroundTheme : normalForegroundTheme
                )
        }
    }
}

#Preview {
    
    VStack(alignment: .center, spacing: 20.0) {
        
        DXTextButton(
            buttonText: "Save",
            onClickAction: {}
        )
        
        DXTextButton(
            buttonText: "Save",
            onClickAction: {},
            isPrimaryThemed: true
        )
    }
}
