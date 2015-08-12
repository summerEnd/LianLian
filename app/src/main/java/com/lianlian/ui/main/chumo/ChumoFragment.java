package com.lianlian.ui.main.chumo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;
import com.lianlian.ui.main.home.HomeFragment;
import com.lianlian.ui.widget.HomeTab;
import com.sp.lib.widget.nav.title.PageStrip;

/**
 * A fragment with a Google +1 button.
 */
public class ChumoFragment extends BaseFragment {

    private ImageView search;
    private ImageView msg;
    private HomeTab hotTab;
    private HomeTab commentTab;
    private HomeTab priceTab;
    private PageStrip pagerTitle;
    private ViewPager pager;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = (ImageView) view.findViewById(R.id.search);
        msg = (ImageView) view.findViewById(R.id.msg);
        hotTab = (HomeTab) view.findViewById(R.id.hotTab);
        commentTab = (HomeTab) view.findViewById(R.id.commentTab);
        priceTab = (HomeTab) view.findViewById(R.id.priceTab);
        pagerTitle = (PageStrip) view.findViewById(R.id.pagerTitle);
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new MyPageAdapter(getChildFragmentManager()));
        pagerTitle.setViewPager(pager);

    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ChumoListFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
