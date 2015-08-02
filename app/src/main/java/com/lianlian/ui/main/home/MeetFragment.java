package com.lianlian.ui.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;

/**
 * Created by Lincoln on 15/8/1.
 */
public class MeetFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meet, container, false);
    }
}
