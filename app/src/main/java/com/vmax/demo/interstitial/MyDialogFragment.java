package com.vmax.demo.interstitial;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vmax.android.ads.api.VmaxSdk;

/**
 * Created by P$ on 24-05-2018.
 */


public  class MyDialogFragment extends DialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_consent, container, false);
        final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("storedData",getActivity().MODE_PRIVATE);
        TextView textView=(TextView) v.findViewById(R.id.btn_no);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        v.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             VmaxSdk.getInstance().setUserConsent(true,true);
                /**Store value to persistent storage to use in further sessions*/
                sharedPreferences.edit().putBoolean("UserConsent",true).commit();
                PersonalizedDailog personalizedDailog=new PersonalizedDailog();
                personalizedDailog.show(getFragmentManager(),"dailog");
                dismiss();
            }
        });
        v.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               VmaxSdk.getInstance().setUserConsent(true,false);
                /**Store value to persistent storage to use in further sessions*/
                sharedPreferences.edit().putBoolean("UserConsent",false).commit();

                NonPersonalizedDailog nonPersonalizedDailog=new NonPersonalizedDailog();
                nonPersonalizedDailog.show(getFragmentManager(),"dailog");
                dismiss();
            }
        });
        return v;
    }


}