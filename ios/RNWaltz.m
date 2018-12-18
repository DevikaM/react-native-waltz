
#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>


@interface RCT_EXTERN_MODULE(WaltzImp, NSObject)
    RCT_EXTERN_METHOD(initManager:(NSString*)licenseKey appUid:(NSString*)appUid)
    RCT_EXTERN_METHOD(login)
    RCT_EXTERN_METHOD(showQR)
@end
