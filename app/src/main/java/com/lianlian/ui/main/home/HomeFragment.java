package com.lianlian.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.ui.BaseFragment;
import com.lianlian.ui.other.message.MessageActivity;
import com.lianlian.utils.FragmentSwitcher;
import com.sp.lib.widget.nav.title.ITab;
import com.sp.lib.widget.nav.title.PageStrip;

/**
 * Created by Lincoln on 15/7/16.
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private PageStrip pagerTitle;
    private FoundFragment mFoundFragment = new FoundFragment();
    private MeetFragment mSearchFragment = new MeetFragment();
    FragmentSwitcher switcher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        switcher = new FragmentSwitcher(getActivity(),R.id.fragmentContainer);
        switcher.put(R.id.found, FoundFragment.class);
        switcher.put(R.id.meet, MeetFragment.class);

        getActivity().findViewById(R.id.msg).setOnClickListener(this);
        getActivity().findViewById(R.id.search).setOnClickListener(this);
        pagerTitle = (PageStrip) getActivity().findViewById(R.id.pagerTitle);

        pagerTitle.setTabListener(new PageStrip.onTabChangeListener() {
            @Override
            public void onSelected(int position, ITab tab) {
                switcher.showFragmentWith(getChildFragmentManager(), tab.getView().getId());
            }

        });
        switcher.showFragmentWith(getChildFragmentManager(), pagerTitle.getTab(0).getView().getId());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msg: {
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            }
            case R.id.search: {
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            }
        }
    }
}
