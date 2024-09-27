//
//  DXDivider.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DXDivider: View {
    
    var body: some View {
        
        AQIColors.shared.divider.common
            .frame(
                width: .infinity,
                height: 2
            )
    }
}

#Preview {
    DXDivider()
}
