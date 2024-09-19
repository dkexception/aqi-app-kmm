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
    
    @Published var path: NavigationPath = NavigationPath()
    
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
            self.path.append(OtherRoutes.Invalid404())
        }
    }
    
    func navigate(route: String) {
        DispatchQueue.main.async {
            self.path.append(route)
        }
    }
    
    func navigateWithObject(obj: Any) {
        DispatchQueue.main.async {
            self.path.append(obj as! AnyHashable)
        }
    }
    
    func navigateClearingStack(route: String) {
        
    }
    
    func navigateClearingStackWithObject(obj: Any) {
        DispatchQueue.main.async {
            self.path.removeLast(self.path.count)
            self.path.append(obj as! AnyHashable)
        }
    }
    
    func navigatePoppingCurrent(route: String) {
        
    }
    
    func navigatePoppingUpto(route: String, popUptoRoute: String, inclusive: Bool) {
        
    }
}
