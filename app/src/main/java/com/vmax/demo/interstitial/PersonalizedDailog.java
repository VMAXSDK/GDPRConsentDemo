package com.vmax.demo.interstitial;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by P$ on 25-05-2018.
 */


public  class PersonalizedDailog extends DialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dailog_prsn, container, false);


        v.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),MainActivity.class);
                startActivity(i);
                dismiss();
            }
        });

        return v;
    }


}