//
//  SnackbarView.swift
//  iosApp
//
//  Created by Dhanesh Katre on 14/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct SnackbarView: View {
    
    let event: SnackbarEvent
    
    @Binding
    var isVisible: Bool
    
    var body: some View {
        
        VStack {
            
            Spacer()
            
            HStack {
                
                Text(event.message)
                    .foregroundColor(.white)
                    .padding(.horizontal)
                
                Spacer()
                
                if let action = event.action {
                    
                    Button {
                        action.action()
                        isVisible = false
                    } label: {
                        Text(action.name)
                    }
                }
            }
            .frame(maxWidth: .infinity)
            .padding()
            .background(Color.black.opacity(0.8))
            .cornerRadius(8)
            .padding(.horizontal)
            .transition(.move(edge: .bottom))
            .animation(.easeInOut, value: true)
        }
    }
}

#Preview {
    
    @State
    var isVisible: Bool = true
    
    return SnackbarView(
        event: SnackbarEvent(
            message: "Hello!!",
            action: SnackbarAction(
                name: "Undo",
                action: {}
            )
        ),
        isVisible: $isVisible
    )
}
