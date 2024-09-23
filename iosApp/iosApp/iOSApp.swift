import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        StartKoinKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            AppContent()
        }
    }
}

#Preview {
    StartKoinKt.doInitKoin()
    return AppContent()
}
