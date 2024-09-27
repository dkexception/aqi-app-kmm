//
//  GuideScreen.swift
//  iosApp
//
//  Created by Mangesh Murhe on 23/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

private struct GuidePage {
    let title: String
    let description: String
    let imageAlignment: Alignment
}

struct GuideScreen: View {
    
    @State var selectedIndex = 0
    
    private let pagerControlWidth: CGFloat = 40
    
    private let guideViewModel: IGuideViewModel = IOSHelpers().provideGuideViewModel()
    
    private let pages = [
        GuidePage(
            title: "Breath Better",
            description: "Understand the air around you, wherever you go with the largest coverage of trusted data.",
            imageAlignment: .leading
        ),
        GuidePage(
            title: "Track Pollution",
            description: "Discover your personal exposure during your daily routine and take action to reduce it",
            imageAlignment: .center
        ),
        GuidePage(
            title: "Controll Exposure",
            description: "During your daily routine discover your personal exposure and take action",
            imageAlignment: .trailing
        )
    ]
    
    var body: some View {
        
        VStack {
            
            HStack {
                
                Spacer()
                
                DXTextButton(
                    buttonText: "Skip",
                    onClickAction: {
                        guideViewModel.onEvent(guideEvent: GuideEvent.OnSkipClicked())
                    }
                )
                
            }.padding(.trailing, 16)
            
            ZStack(alignment:.top) {
                
                TabView(selection:$selectedIndex) {
                    ForEach(0..<pages.count, id: \.self) { index in
                        PageView(pageDetails: pages[index])
                    }
                    
                }.tabViewStyle(.page(indexDisplayMode: .never))
                
                VStack(alignment:.center) {
                    
                    Spacer()
                    
                    CustomPager(
                        totalIndex: pages.count,
                        selectedIndex: selectedIndex
                    )
                    .frame(width: pagerControlWidth, height: 6)
                    .padding(.top, 80)
                    
                    Spacer()
                }
            }
            
            Spacer(minLength: 2)
            
            DXPrimaryButton(
                buttonText: "Get started!",
                onClickAction: {
                    guideViewModel.onEvent(guideEvent: GuideEvent.OnGetStartedClicked())
                }
            )
            
            Spacer()
        }.navigationBarBackButtonHidden(true)
    }
}

private struct PageView: View {
    
    let pageDetails: GuidePage
    
    var body: some View {
        
        GeometryReader { reader in
            
            VStack(spacing: 16) {
                
                Image("GuideScreenImage")
                    .resizable()
                    .scaledToFill()
                    .frame(
                        width: reader.size.width * 0.55,
                        alignment: pageDetails.imageAlignment
                    )
                    .padding(.horizontal, 32)
                    .frame(height: 315)
                
                Spacer(minLength: 100)
                
                Text(pageDetails.title)
                    .font(.titleF)
                    .multilineTextAlignment(.center)
                
                Text(pageDetails.description)
                    .font(.body1F)
                    .multilineTextAlignment(.center)
                
                Spacer()
            }
            .padding(.horizontal, 32)
            .background(Color.white)
            
            Spacer()
        }
    }
}

private struct CustomPager: View {
    
    let totalIndex: Int
    
    let selectedIndex: Int
    
    @Namespace var animation
    
    var body: some View {
        
        HStack {
            
            ForEach(0..<totalIndex, id: \.self) { index in
                
                if selectedIndex == index {
                    Circle()
                        .fill(.gray.opacity(0.3))
                        .frame(height: 5)
                        .overlay(
                            content: {
                                Circle()
                                    .fill(.blue)
                                    .frame(height: 5)
                                    .matchedGeometryEffect(
                                        id: "IndicatorAnimationId",
                                        in: animation
                                    )
                            }
                        )
                } else {
                    
                    Circle()
                        .fill(.gray.opacity(0.3))
                        .frame(height: 5)
                }
            }
        }.animation(.spring, value: UUID())
    }
}

#Preview {
    GuideScreen()
}
