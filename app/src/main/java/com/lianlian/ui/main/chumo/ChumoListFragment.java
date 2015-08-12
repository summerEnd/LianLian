package com.lianlian.ui.main.chumo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.adapter.chumo.ChumoGridAdapter;
import com.lianlian.adapter.decor.PaddingDecoration;
import com.lianlian.entity.Chumo;
import com.lianlian.ui.BaseFragment;

import java.util.ArrayList;

public class ChumoListFragment extends BaseFragment {

    private RecyclerView recycleView;

    public ChumoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chumo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrayList<Chumo> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new Chumo());
        }
        recycleView.setAdapter(new ChumoGridAdapter(getActivity(), data));
        recycleView.addItemDecoration(new PaddingDecoration(6, 6, 6, 6));
    }

    private void initialize() {

    }
}
