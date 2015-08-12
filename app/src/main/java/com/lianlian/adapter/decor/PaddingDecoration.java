package com.lianlian.adapter.decor;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lincoln on 15/8/12.
 */
public class PaddingDecoration extends RecyclerView.ItemDecoration {
    int left;
    int top;
    int right;
    int bottom;

    public PaddingDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(left, top, right, bottom);
    }
}
