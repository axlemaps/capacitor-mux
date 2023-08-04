import Foundation

@objc public class CapacitorMux: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
