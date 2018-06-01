package com.vmax.demo.interstitial;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vmax.android.ads.api.VmaxAdView;
import com.vmax.android.ads.common.VmaxAdListener;
import com.vmax.android.ads.exception.VmaxAdError;


public class MainActivity extends AppCompatActivity {


    private LinearLayout UI_Container;
    public   VmaxAdView vmaxAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /** UI Container */
        UI_Container=(LinearLayout) findViewById(R.id.UI_Container);
        cacheInterstitial();
      /** Show Interstitial on Click Event*/
        UI_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vmaxAdView.getAdState() == VmaxAdView.AdState.STATE_AD_READY) {
                    vmaxAdView.showAd();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Ad Not Ready Yet Please Try again after a few moments", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /** Method for adding Interstitial */

    public void cacheInterstitial()
    {
        /** Initializing vmaxAdView with an Adspot,*/
         vmaxAdView = new VmaxAdView(this,"bb73d165",VmaxAdView.UX_INTERSTITIAL);

        /** To Fetch Your AdvId you can check your device's Google settings under ads subMenu Or You can Run this app Once and check
         * the logs for 'AdRequested with url' under the tag vmax, from the url your Advid
         * would be one of the parameters in the post request eg. advid=2cf626f0-08ac-4a4d-933c-00ecd0256cf4*/

/** DON'T INCLUDE vmaxAdView.setTestDevices() WHILE GOING LIVE WITH YOUR PROJECT AS THIS SERVES ONLY TEST ADS;*/
        vmaxAdView.setTestDevices(VmaxAdView.TEST_via_ADVID,"<REPLACE WITH YOUR ADVID>");



        vmaxAdView.setAdListener(new VmaxAdListener() {
            @Override
            public void onAdError(VmaxAdError error) {

         /**Handle Ad Error Event*/
                Toast.makeText(getApplicationContext(),
                        "Failed To Load Ad Please Try Again Later", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClose() {

            }

            @Override
            public void onAdMediaEnd(boolean b, long l) {

            }

            @Override
            public void onAdReady(VmaxAdView adView) {
           Toast.makeText(getApplicationContext(),"onAdReady", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdRender() {
                super.onAdRender();
               if( vmaxAdView.getAdState()== VmaxAdView.AdState.STATE_AD_STARTED)
               {

               }
            }
        });

        vmaxAdView.cacheAd();


    }


   
    /** Handle vmaxAdView object for Activity Lifecycle changes */


    @Override
    protected void onDestroy() {
        if (vmaxAdView != null) {
       /** To Destroy vmaxAdView when Activity Is No Longer Available  */
            vmaxAdView.onDestroy();
        }
        super.onDestroy();
    }








}
