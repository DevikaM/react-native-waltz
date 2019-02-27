package com.rnwaltz;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.waltzapp.androidsdk.WaltzSDK;
import com.waltzapp.androidsdk.WaltzCode;
import com.waltzapp.androidsdk.WaltzLogInFragment;
import com.waltzapp.androidsdk.WaltzTransactionFragment;
import com.waltzapp.androidsdk.callback.LogInCallback;
import com.waltzapp.androidsdk.callback.TransactionCallback;
import com.waltzapp.androidsdk.callback.WaltzCallback;
import com.waltzapp.androidsdk.pojo.DDInfos;

import android.util.Log;

public class WaltzActivity extends FragmentActivity {
    private MyDialog mMyDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waltz_fragment_activity);
        String type = getIntent().getStringExtra("type");

        switch (type){
            case "login": {
                WaltzLogInFragment fragment = WaltzLogInFragment.newInstance(new LogInCallback() {
                    @Override
                    public void onComplete(WaltzCode waltzCode) {
                        Log.i("WaltzFragmentActivity", "completed...");
                        showDialog(waltzCode, null);

                    }
                });

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            }
            case "qr_code": {
                WaltzTransactionFragment fragment = WaltzTransactionFragment.newInstance(new TransactionCallback() {
                    @Override
                    public void onTransactionDone(WaltzCode waltzCode) {
                        showDialog(waltzCode, null);
                    }

                    @Override
                    public void onTransactionDone(WaltzCode waltzCode, DDInfos ddInfos) {
                        showDialog(waltzCode, ddInfos);
                    }
                });
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            }

        }
        /*WaltzLogInFragment fragment = WaltzLogInFragment.newInstance(new LogInCallback() {
            @Override
            public void onComplete(WaltzCode waltzCode) {
                Log.i("WaltzFragmentActivity", "completed...");
                showDialog(waltzCode, null);

            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .addToBackStack(null)
                .commit();*/
    }

    private void showDialog(WaltzCode waltzCode, DDInfos ddInfos) {
        if (mMyDialog == null) {
            mMyDialog = MyDialog
                    .with(waltzCode)
                    .setListener(new MyDialog.Listener() {
                        @Override
                        public void onDismiss() {
                            /*getSupportFragmentManager().popBackStack();
                            mMyDialog = null;*/
                            Intent data = new Intent();
                            String text = "SUCCESS";
                            data.setData(Uri.parse(text));
                            setResult(RESULT_OK, data);
                            finish();
                        }
                    })
                    .setDDInfos(ddInfos);
            mMyDialog.show(getSupportFragmentManager(), "dialog");
        } else if (ddInfos != null) {
            mMyDialog.refreshDDInfos(ddInfos);
        }
    }


        @Override
    public void onBackPressed() {
        Intent data = new Intent();
        String text = "no result";
        data.setData(Uri.parse(text));
        setResult(RESULT_OK, data);
        finish();
    }
}
