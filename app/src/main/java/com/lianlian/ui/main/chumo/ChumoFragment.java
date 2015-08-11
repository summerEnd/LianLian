package com.lianlian.ui.main.chumo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;

/**
 * A fragment with a Google +1 button.
 */
public class ChumoFragment extends BaseFragment {

    public ChumoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        //Find the +1 button

        return view;
    }




}
