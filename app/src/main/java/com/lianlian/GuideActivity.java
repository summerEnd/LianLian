package com.lianlian;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.login.LoginActivity;
import com.sp.lib.common.support.adapter.GuidePagerAdapter;


public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ViewPager pager = new ViewPager(this);
        setContentView(pager);
        pager.setAdapter(new GuidePagerAdapter(this, new int[]{R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4}));
        //监听滑动事件
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean exit;
            int lastPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //如果是最后一页，并且也不能向右滑动，可以接结束
                exit = (positionOffsetPixels == lastPosition) && !pager.canScrollHorizontally(1);
                lastPosition = positionOffsetPixels;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE && exit) {
                    //滑动结束，根据exit判断是否可以结束
                    startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                }
            }
        });
    }


}
