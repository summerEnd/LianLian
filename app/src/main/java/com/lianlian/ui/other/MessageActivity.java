package com.lianlian.ui.other;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.lianlian.R;
import com.sp.lib.widget.nav.title.ITab;
import com.sp.lib.widget.nav.title.PageStrip;

public class MessageActivity extends BaseActivity {

    private FragmentManager mFragmentManager;

    private Fragment mCurrentFragment = null;

    private LianLianFragment mLLFragment;
    private ChuMoFragment mCMFragment;
    private LiaoTianFragment mLTFragment;
    private TongZhiFragment mTZFragment;
    private PageStrip pagerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_layout);
        mFragmentManager = getFragmentManager();
        mLLFragment = new LianLianFragment();
        mCMFragment = new ChuMoFragment();
        mLTFragment = new LiaoTianFragment();
        mTZFragment = new TongZhiFragment();
        initialize();
    }


    private void startFragment(Fragment fragment) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mCurrentFragment == null) {
            ft.add(R.id.container, fragment);
        } else {
            if (mCurrentFragment == fragment) {
                return;
            } else {
                ft.hide(mCurrentFragment);
                if (fragment.isAdded()) {
                    ft.show(fragment);
                } else {
                    ft.add(R.id.container, fragment);
                }
            }
        }
        mCurrentFragment = fragment;
        ft.commit();
    }

    public void deleteDialog(View v) {
        OptionsDialog dialog = new OptionsDialog();
        dialog.show(mFragmentManager, "");
    }

    private void initialize() {

        pagerTitle = (PageStrip) findViewById(R.id.pagerTitle);
        pagerTitle.setOnTitleChangeListener(new PageStrip.OnTitleChangeListener() {
            @Override
            public void onSelected(int position, ITab tab) {
                switch (position) {
                    case 0: {
                        startFragment(mLLFragment);

                        break;
                    }
                    case 1: {
                        startFragment(mCMFragment);

                        break;
                    }
                    case 2: {
                        startFragment(mLTFragment);

                        break;
                    }
                    case 3: {
                        startFragment(mTZFragment);

                        break;
                    }
                }
            }

            @Override
            public void onUnSelected(int position, ITab tab) {

            }
        });
    }
}
