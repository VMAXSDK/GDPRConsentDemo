package com.vmax.demo.interstitial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vmax.android.ads.api.VmaxSdk;
import com.vmax.android.ads.common.RegionCheckListener;

public class ConsentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent2);

        final SharedPreferences sharedPreferences = getSharedPreferences("storedData", MODE_PRIVATE);

        if (!sharedPreferences.contains("isGdprApplicable")) {

            /**Helper API To check whether user falls under EEA
             * Alternatively you can use your own logic to determine if user falls under EEA*/

            VmaxSdk.getInstance().isUserInEEA(ConsentActivity.this, new RegionCheckListener() {
                @Override
                public void onSuccess(boolean isGdprApplicable) {
                    if (isGdprApplicable) {
                        /**Store value to persistent storage to use in further sessions*/
                        sharedPreferences.edit().putBoolean("isGdprApplicable", isGdprApplicable).commit();

                        MyDialogFragment myDialogFragment = new MyDialogFragment();
                        myDialogFragment.show(getFragmentManager(), "dailog");
                    }
                }

                @Override
                public void onFailure(int errorCode) {

                }
            });
        } else {
            boolean isGdprApplicable = sharedPreferences.getBoolean("isGdprApplicable", false);
            if (isGdprApplicable) {
                if (sharedPreferences.contains("UserConsent")) {

                    boolean storedUserConsent = sharedPreferences.getBoolean("UserConsent", false);

                    /**User Consent needs to be set once every App Session*/
                    VmaxSdk.getInstance().setUserConsent(isGdprApplicable,storedUserConsent);
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
            }


        }
    }
}
