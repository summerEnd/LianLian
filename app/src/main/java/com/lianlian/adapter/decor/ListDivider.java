package com.lianlian.adapter.decor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lincoln on 15/7/21.
 */
public class ListDivider extends RecyclerView.ItemDecoration {
    Drawable mDivider;
    int divHeight;

    public ListDivider(Drawable divider) {
        this.mDivider = divider;
        divHeight = divider.getIntrinsicHeight();
        if (divHeight <= 0) {
            divHeight = 1;
        }
    }

    public void setDivHeight(int divHeight) {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, divHeight);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            mDivider.setBounds(
                    child.getLeft(), child.getBottom(), child.getRight(), child.getBottom() + divHeight);
        }
    }
}
