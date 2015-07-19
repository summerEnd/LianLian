package com.lianlian.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lianlian.R;
import com.sp.lib.widget.nav.SimpleTab;
import com.sp.lib.widget.nav.title.TextPageTab;

/**
 * Created by Lincoln on 15/7/16.
 * 首页tab
 */
public class HomeTab extends TextPageTab {
    public HomeTab(Context context) {
        super(context);
    }

    public HomeTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeTab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onTabSelected(boolean selected) {
        if (selected) {
            setTextColor(getResources().getColor(R.color.windowTitleColor));
        } else {
            setTextColor(0xffffffff);

        }

    }
}
