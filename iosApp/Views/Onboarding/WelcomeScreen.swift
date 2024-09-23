//
//  WelcomeScreen.swift
//  iosApp
//
//  Created by Dhanesh Katre on 16/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct WelcomeScreen: View {
    
    var body: some View {
        
        ZStack {
            
            Image("welcome_bg")
                .resizable()
                .aspectRatio(contentMode: .fill)
                .ignoresSafeArea()
            
            Image("app_logo")
        }
    }
}

#Preview {
    WelcomeScreen()
}
