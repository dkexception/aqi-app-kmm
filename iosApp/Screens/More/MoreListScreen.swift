//
//  MoreListScreen.swift
//  iosApp
//
//  Created by Dhanesh Katre on 25/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct MoreListScreen: View {
    
    @StateObject
    var moreListViewModelAdapter = KotlinAdapter<IMoreListViewModel, MoreListScreenState>(
        createViewModel: { _ in
            IOSHelpers().provideMoreListViewModel()
        },
        getState: { $0.state }
    )
    
    private var section1List = [
        ("Profile", MoreListItem.profile)
    ]
    
    
    var body: some View {
        
        ZStack {
            
            VStack {
                Image("MoreListBackground")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                
                Spacer()
            }.ignoresSafeArea()
            
            ScrollView {
                
                VStack {
                    
                    Spacer(minLength: 50)
                    
                    Image("AppLogo")
                        .resizable()
                        .frame(width: 80, height: 60)
                        .padding(40)
                        .background(AQIColors.shared.accentColor.light)
                        .clipShape(Circle())
                    
                    Spacer(minLength: 10)
                    
                    Text(moreListViewModelAdapter.state.userName)
                        .font(.bodyF)
                    
                    Spacer(minLength: 8)
                    
                    Text(moreListViewModelAdapter.state.emailId)
                        .font(.body1F)
                    
                    Spacer(minLength: 16)
                    
                    DXDivider()
                    
                    Spacer(minLength: 16)
                    
                    ForEach(section1List.indices, id: \.self) { index in
                        
                        
                        
                    }
                    
                    Spacer()
                }
            }
            .padding()
        }
    }
}

struct MoreListItemView: View {
    
    var body: some View {
        HStack {
            
        }
    }
}

#Preview {
    MoreListScreen()
}
