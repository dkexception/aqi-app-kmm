//
//  Primary.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

protocol Primary {
    
    var common: Color { get }
    
    var light: Color { get }
    
    var dark: Color { get }
}

class PrimaryDefault: Primary {
    
    var common: Color = Color(hex: 0xFF1882FF)
    
    var light: Color = Color(hex: 0xFFE3F2FF)
    
    var dark: Color = Color(hex: 0xFF245DD8)
}
