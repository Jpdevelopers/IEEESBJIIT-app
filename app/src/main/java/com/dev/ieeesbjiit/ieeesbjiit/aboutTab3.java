package com.dev.ieeesbjiit.ieeesbjiit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yugank on 27/9/17.
 */

public class aboutTab3 extends Fragment {
    private static final String TAG= "TAB1Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.aboutieee_tab3,container,false);

        return view;

    }
}
