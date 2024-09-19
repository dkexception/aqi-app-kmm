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
    
    let welcomeViewModel = WelcomeViewModel()
    
    var body: some View {
        Text("Welcome!")
    }
}

#Preview {
    WelcomeScreen()
}
