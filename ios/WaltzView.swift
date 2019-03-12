import UIKit

class WaltzView: UIView {
    
    var WaltzObj = WaltzImp()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        self.WaltzObj.initManager("67dcbd65-c9ff-40a1-8698-01353239cd1b",appUid: "4e02e031-7968-4b19-9b8c-b5132ef40fb1")
        //self.WaltzObj.login()
        
        self.WaltzObj.showQR(view: self, vc: RCTPresentedViewController())
        
    }
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
