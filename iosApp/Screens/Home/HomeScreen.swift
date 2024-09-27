//
//  HomeScreen.swift
//  iosApp
//
//  Created by Dhanesh Katre on 23/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct HomeScreen: View {
    
    @StateObject
    var homeViewModelAdapter = KotlinAdapter<IHomeViewModel, HomeScreenState>(
        createViewModel: { _ in
            IOSHelpers().provideHomeViewModel()
        },
        getState: { $0.state }
    )
    
    var body: some View {
        
        ScrollView {
            
            VStack {
                
                HStack {
                    
                    VStack(alignment: .leading) {
                        Text("Welcome 👋")
                            .font(.bodyF)
                        
                        let name = if homeViewModelAdapter.state.userName.isEmpty {
                            "User"
                        } else {
                            homeViewModelAdapter.state.userName
                        }
                        
                        Text(name)
                            .font(.titleF)
                    }
                    
                    Spacer()
                    
                    Button(
                        action: {
                            homeViewModelAdapter.viewModel.onEvent(
                                homeEvent: HomeEvent.OnSettingsClicked()
                            )
                            print("button pressed")
                        }
                    ) {
                        Image(systemName: "gearshape.fill")
                            .renderingMode(Image.TemplateRenderingMode?.init(Image.TemplateRenderingMode.original))
                    }
                }
                .padding()
            }
            
            Spacer()
            
        }
        .refreshable {
            
        }
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    HomeScreen()
}
