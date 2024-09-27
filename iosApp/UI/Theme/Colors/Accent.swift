//
//  Accent.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

protocol Accent {
    
    var common: Color { get }
    
    var light: Color { get }
    
    var dark: Color { get }
}

class AccentDefault: Accent {
    
    var common: Color = Color(hex: 0xFF00CD85)
    
    var light: Color = Color(hex: 0xFFBDEDD4)
    
    var dark: Color = Color(hex: 0xFF00A056)
}
