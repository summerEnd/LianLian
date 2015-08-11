package com.lianlian.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.R;

/**
 * Created by Lincoln on 15/7/20.
 */
public class HomeItem extends FrameLayout {
    public TextView title;
    private ImageView icon;
    public ImageView ivimage;
    private View contentLayout;
    public TextView tvname;
    public TextView tvlabel1;
    public TextView tvlabel2;
    private View layout;
    private View titleLayout;

    public HomeItem(Context context) {
        this(context, null);
    }

    public HomeItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        layout = LayoutInflater.from(context).inflate(R.layout.layout_home_item, null, false);
        title = (TextView) layout.findViewById(R.id.title);
        ivimage = (ImageView) layout.findViewById(R.id.iv_image);
        tvname = (TextView) layout.findViewById(R.id.tv_name);
        tvlabel1 = (TextView) layout.findViewById(R.id.tv_label1);
        tvlabel2 = (TextView) layout.findViewById(R.id.tv_label2);
        contentLayout = layout.findViewById(R.id.contentLayout);
        icon = (ImageView) layout.findViewById(R.id.icon);
        titleLayout = layout.findViewById(R.id.titleLayout);
        addView(layout);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HomeItem);
        title.setText(a.getString(R.styleable.HomeItem_home_item_title));
        icon.setImageResource(a.getResourceId(R.styleable.HomeItem_home_item_icon, 0));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        layout.measure(widthMeasureSpec, heightMeasureSpec);
        int width = layout.getMeasuredWidth();
        int titleHeight = titleLayout.getMeasuredHeight();
        layout.measure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(width+titleHeight,MeasureSpec.EXACTLY));
        setMeasuredDimension(width,layout.getMeasuredHeight());
    }

}
