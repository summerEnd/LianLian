package com.lianlian.ui.main.lian;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LianFragment extends BaseFragment {


    public LianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lian, container, false);
    }


}
