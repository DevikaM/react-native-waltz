import Foundation
import UIKit
import AVFoundation
import WaltzAccess

func requestCameraPermission() {
    if AVCaptureDevice.responds(to: #selector(AVCaptureDevice.requestAccess(for:completionHandler:))) {
        AVCaptureDevice.requestAccess(for: .video, completionHandler: { granted in
            if granted {
                PermissionsChecker.sharedInstance.cameraPermissionsHaveBeenGranted()
            }
        })
    }
}


@objc(WaltzImp)
class WaltzImp : NSObject {
    
    @objc func initManager(_ licenseKey: String, appUid: String) -> Void {
        requestCameraPermission()
        PermissionsChecker.sharedInstance.requestLocationPermission()
        let _ = WaltzSDKMgr.sharedManager.initManager(licenseKey: licenseKey, appUid: appUid).setLoginVisual(backgroundImage: UIImage(named: "background"), logo: UIImage(named: "logo"), backgroundColor: UIColor.black, primaryColor: UIColor(red: 46/255, green: 104/255, blue: 116/255, alpha: 1.0))
    }
    
    @objc func login() -> Void {
        WaltzSDKMgr.sharedManager.logIn()
    }
    
    @objc func showQR(view: UIView, vc: UIViewController?) -> Void {
        WaltzSDKMgr.sharedManager.beginTransaction(parentView: view, parentVC: vc)
    }
    
}





