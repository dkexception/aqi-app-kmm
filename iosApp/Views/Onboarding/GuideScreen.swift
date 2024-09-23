//
//  GuideScreen.swift
//  iosApp
//
//  Created by Dhanesh Katre on 16/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct GuideScreen: View {
    
    let guideViewModel: IGuideViewModel = IOSHelpers().provideGuideViewModel()
    
    var body: some View {
        
        Button("Guide Next") {
            guideViewModel.onEvent(guideEvent: GuideEvent.OnGetStartedClicked())
        }
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    GuideScreen()
}
