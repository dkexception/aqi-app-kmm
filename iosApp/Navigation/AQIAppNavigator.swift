//
//  AQIAppNavigator.swift
//  iosApp
//
//  Created by Dhanesh Katre on 14/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import Shared

class AQIAppNavigator: Navigator, ObservableObject {
    
    let isAndroid: Bool = false
    
    @Published var path: [String] = []
    
    init() {
        (IOSHelpers().navigator as? IOSNavigator)?.setNavigator(navigator: self)
    }
    
    func canGoBack() -> Bool {
        return !path.isEmpty
    }
    
    func goBack() {
        DispatchQueue.main.async {
            self.path.removeLast()
        }
    }
    
    func handleIllegalNavigation() {
        DispatchQueue.main.async {
            self.path.append(Constants.NavigationRoutes().SCREEN_404)
        }
    }
    
    func navigate(route: String) {
        DispatchQueue.main.async {
            self.path.append(route)
        }
    }
    
    func navigateClearingStack(route: String) {
        
    }
    
    func navigatePoppingCurrent(route: String) {
        
    }
    
    func navigatePoppingUpto(route: String, popUptoRoute: String, inclusive: Bool) {
        
    }
}
