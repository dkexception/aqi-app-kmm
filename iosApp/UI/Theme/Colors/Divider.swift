//
//  Divider.swift
//  iosApp
//
//  Created by Dhanesh Katre on 27/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

protocol Divider {
    
    var common: Color { get }
}

class DividerDefault: Divider {
    
    var common: Color = Color(hex: 0xFFF3F4F6)
}
