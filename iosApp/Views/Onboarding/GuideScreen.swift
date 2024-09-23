//
//  GuideScreen.swift
//  iosApp
//
//  Created by Mangesh Murhe on 23/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct GuidePage {
    let title: String
    let imageName: String
    let description: String
    let imageAlignment: Alignment
}

struct GuideScreen: View {
    let pages = [GuidePage(title: "Breath Better", imageName: "GuideScreenImage", description: "Understand the air around you, wherever you go with the largest coverage of trusted data.",imageAlignment: .leading), GuidePage(title: "Track Pollution", imageName: "GuideScreenImage", description: "Discover your personal exposure during your daily routine and take action to reduce it", imageAlignment: .center), GuidePage(title: "Controll Exposure", imageName: "GuideScreenImage", description: "During your daily routine discover your personal exposure and take action", imageAlignment: .trailing)]
    var body: some View {
        TabView {
            ForEach(0..<pages.count, id: \.self) { index in
                PageView(pageDetails: pages[index])
            }
            
        }.tabViewStyle(.page(indexDisplayMode: .always))
    }
}

struct PageView: View {
    let pageDetails: GuidePage
    
    var body: some View {
        VStack(spacing: 32) {
            HStack {
                Spacer()
                Button("Skip") {
                    
                }.padding(.trailing, 16)
            }
            Image(pageDetails.imageName)
                .resizable()
                .frame(height: 315)
                .scaledToFill()
                .frame(width: 300, alignment: pageDetails.imageAlignment)
                .padding(.horizontal, 32)
    
            VStack(spacing: 32) {
                Text(pageDetails.title)
                    .font(.title)
                    .multilineTextAlignment(.center)
                Text(pageDetails.description)
                    .font(.body1)
                    .multilineTextAlignment(.center)
            }.padding(.horizontal, 32)
            Spacer()
        }
    }
}
#Preview {
    GuideScreen()
}
