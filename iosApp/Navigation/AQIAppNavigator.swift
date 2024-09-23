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
    
    @Published var path: NavigationPath = NavigationPath()
    
    init() {
        (IOSHelpers().navigator as? IOSNavigator)?.setNavigator(navigator: self)
    }
    
    let isAndroid: Bool = false
    
    func navigate(obj: Any) {
        DispatchQueue.main.async {
            
            if let hObj = obj as? (any Hashable) {
                self.path.append(hObj)
            }
        }
    }
    
    func navigateClearingStack(obj: Any) {
        DispatchQueue.main.async {
            
            self.path.removeLast(self.path.count)
            
            if let hObj = obj as? (any Hashable) {
                self.path.append(hObj)
            }
        }
    }
    
    func navigatePoppingCurrent(obj: Any) {
        DispatchQueue.main.async {
            
            self.path.removeLast(1)
            
            if let hObj = obj as? (any Hashable) {
                self.path.append(hObj)
            }
        }
    }
    
    func navigatePoppingUpto(obj: Any, popUptoObj: Any, inclusive: Bool) {
        
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
}
