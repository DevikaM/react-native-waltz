import Foundation
import UIKit

@objc(WaltzManager)
class WaltzManager : RCTViewManager {
    
    var LocalWaltzView: WaltzView!
    
    override func view() -> UIView! {
        let frame = RCTPresentedViewController()?.view.frame
        // RN messes up with backgroundColor and size of Bridged View. This is why a Wrapper View is needed
        let wrapperView = UIView(frame: CGRect(x: 0, y: 0, width: (frame?.width)!, height: (frame?.height)!))
        LocalWaltzView = WaltzView(frame: CGRect(x: -132, y: 0, width: 270, height: 550))
        wrapperView.addSubview(LocalWaltzView)
        return wrapperView;
    }
    
    @objc override static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
}
