package com.lianlian.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;
import com.lianlian.utils.FragmentUtils;
import com.sp.lib.widget.nav.title.ITab;
import com.sp.lib.widget.nav.title.PageStrip;
import com.sp.lib.widget.pager.BannerPager;

/**
 * Created by Lincoln on 15/7/16.
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private PageStrip pagerTitle;
    private MeetFragment mMeetFragment = new MeetFragment();
    private FoundFragment mFoundFragment = new FoundFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pagerTitle = (PageStrip) view.findViewById(R.id.pagerTitle);

        pagerTitle.setOnTitleChangeListener(new PageStrip.OnTitleChangeListener() {
            @Override
            public void onSelected(int position, ITab tab) {
                if (position == 0) {
                    FragmentUtils.switchFragment(getChildFragmentManager(),
                            mFoundFragment, mMeetFragment,
                            R.id.fragmentContainer);

                } else {
                    FragmentUtils.switchFragment(getChildFragmentManager(),
                            mMeetFragment, mFoundFragment,
                            R.id.fragmentContainer);
                }
            }

            @Override
            public void onUnSelected(int position, ITab tab) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pagerTitle.getTab(0).getView().performClick();

            }
        }, 200);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.msg).setOnClickListener(this);
        getActivity().findViewById(R.id.search).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msg: {
                new BecomePaoFriends().show(getChildFragmentManager(), "sad");
                break;
            }
            case R.id.search:{
                break;
            }
        }
    }
}
