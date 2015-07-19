package com.lianlian.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;
import com.sp.lib.widget.nav.title.ITab;
import com.sp.lib.widget.nav.title.PageStrip;
import com.sp.lib.widget.pager.BannerPager;

/**
 * Created by Lincoln on 15/7/16.
 * 首页
 */
public class HomeFragment extends BaseFragment {
    private PageStrip pagerTitle;
    private BannerPager pager;
    private MeetFragment fragment;

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
                if (fragment == null) {
                    fragment = new MeetFragment();
                    getChildFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment).commit();

                }
            }

            @Override
            public void onUnSelected(int position, ITab tab) {

            }
        });

        pagerTitle.getTab(0).getView().performClick();

    }

}
