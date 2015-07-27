package com.lianlian.ui.home;


import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;
import com.lianlian.ui.rank.FaceRankActivity;
import com.lianlian.ui.rank.HotRankActivity;
import com.lianlian.ui.rank.PayRankActivity;
import com.lianlian.ui.rank.RankActivity;
import com.lianlian.ui.rank.TuhaoRankActivity;
import com.lianlian.ui.widget.HomeItem;
import com.lianlian.utils.ImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sp.lib.widget.pager.BannerPager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetFragment extends BaseFragment implements View.OnClickListener {


    private BannerPager banner;
    private HomeItem rankhao;
    private HomeItem rankhot;
    private HomeItem rankmoney;
    private HomeItem rankface;
    private HomeItem rankmeet;
    private HomeItem rankshow;


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
        ArrayList<String> images = new ArrayList<>();
        images.add("http://img1.imgcdns.com/allimg/140908/23454B1W-0.jpg");
        images.add("http://img1.imgcdns.com/allimg/140908/23454B1W-0.jpg");
        images.add("http://img1.imgcdns.com/allimg/140908/23454B1W-0.jpg");
        banner.setImageUrls(images);
        banner.start(3000);
        initialize();

    }


    private void initialize() {

        rankhao = (HomeItem) getActivity().findViewById(R.id.rank_hao);
        rankhot = (HomeItem) getActivity().findViewById(R.id.rank_hot);
        rankmoney = (HomeItem) getActivity().findViewById(R.id.rank_money);
        rankface = (HomeItem) getActivity().findViewById(R.id.rank_face);
        rankmeet = (HomeItem) getActivity().findViewById(R.id.rank_meet);
        rankshow = (HomeItem) getActivity().findViewById(R.id.rank_show);
        rankhao.setOnClickListener(this);
        rankhot.setOnClickListener(this);
        rankmoney.setOnClickListener(this);
        rankface.setOnClickListener(this);
        rankmeet.setOnClickListener(this);
        rankshow.setOnClickListener(this);

        setData(rankhao);
        setData(rankhot);
        setData(rankmoney);
        setData(rankface);
        setData(rankmeet);
        setData(rankshow);
    }

    void setData(HomeItem item) {
        String url = "http://f.hiphotos.baidu.com/image/pic/item/fc1f4134970a304e006c1b7ed2c8a786c8175ce3.jpg";
        ImageLoader.getInstance().displayImage(url, item.ivimage, ImageOptions.getStandard());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank_hao: {
                startActivity(new Intent(v.getContext(), TuhaoRankActivity.class));

                break;
            }
            case R.id.rank_hot: {
                startActivity(new Intent(v.getContext(), HotRankActivity.class));

                break;
            }
            case R.id.rank_money: {
                startActivity(new Intent(v.getContext(), PayRankActivity.class));

                break;
            }
            case R.id.rank_face: {
                startActivity(new Intent(v.getContext(), FaceRankActivity.class));

                break;
            }
            case R.id.rank_meet: {
                new ReceiveFriends().show(getChildFragmentManager(),"hh");
                break;
            }
            case R.id.rank_show: {

                break;
            }
        }
    }
}
