package com.lianlian.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.adapter.MeetAdapter;
import com.lianlian.entity.Meet;
import com.sp.lib.widget.pager.BannerPager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetFragment extends Fragment {


    private BannerPager banner;
    private RecyclerView recyclerView;

    public MeetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = (BannerPager) view.findViewById(R.id.banner);
        ArrayList<String> images=new ArrayList<>();
        images.add("http://img1.imgcdns.com/allimg/140908/23454B1W-0.jpg");
        images.add("http://img1.imgcdns.com/allimg/140908/23454B1W-0.jpg");
        images.add("http://img1.imgcdns.com/allimg/140908/23454B1W-0.jpg");
        banner.setImageUrls(images);
        banner.start(3000);

        recyclerView= (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrayList<Meet> data=new ArrayList<>();

        recyclerView.setAdapter(new MeetAdapter(getActivity(), data));
    }

}
