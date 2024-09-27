//
//  AQIColors.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

protocol IAQIColors {
    
    var primaryColor: Primary { get }
    
    var accentColor: Accent { get }
    
    var divider: Divider { get }
}

final class AQIColors: IAQIColors {
    
    @Environment(\.colorScheme) var systemColorScheme
    
    static let shared = AQIColors()
    
    var isSystemInDarkTheme: Bool {
        switch systemColorScheme {
        case .light:
            return false
        case .dark:
            return true
        default:
            return false
        }
    }
    
    let primaryColor: Primary = PrimaryDefault()
    
    let accentColor: Accent = AccentDefault()
    
    let divider: Divider = DividerDefault()
}
