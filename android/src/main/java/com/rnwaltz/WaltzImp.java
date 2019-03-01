
package com.rnwaltz;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.waltzapp.androidsdk.WaltzSDK;
import com.waltzapp.androidsdk.WaltzLogInFragment;
import com.waltzapp.androidsdk.callback.LogInCallback;
import com.waltzapp.androidsdk.WaltzCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;



public class WaltzImp extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private static final int WALTZ_LOGIN = 9933117;
    private static final int WALTZ_TRANSACTION = 9933118;

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            if (requestCode == WALTZ_LOGIN) {
                Log.i("RNRnWaltzModule", "result came...");
            }
        }
    };

  public WaltzImp(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
      reactContext.addActivityEventListener(mActivityEventListener);
  }

  @Override
  public String getName() {
    return "WaltzImp";
  }

  @ReactMethod
  public void initManager(String licenseKey,String appUid ) {
      WaltzSDK.getInstance()
            	.setContext(this.reactContext)
            	.setAppUid(appUid)
            	.setLicenseKey(licenseKey)
            	.init();
      WaltzSDK wsdk = WaltzSDK.getInstance();

  }

  @ReactMethod
  public void login(){
      Activity currentActivity = getCurrentActivity();
      Intent intent = new Intent(this.reactContext, WaltzActivity.class);
      intent.putExtra("type", "login");
      currentActivity.startActivityForResult(intent, WALTZ_TRANSACTION);
  }

  @ReactMethod
    public void showQR(){
      Activity currentActivity = getCurrentActivity();
      Intent intent = new Intent(this.reactContext, WaltzActivity.class);
      intent.putExtra("type", "qr_code");
      currentActivity.startActivityForResult(intent, WALTZ_LOGIN);
  }



}
