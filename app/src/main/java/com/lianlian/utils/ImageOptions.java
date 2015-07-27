package com.lianlian.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sp.lib.common.image.RoundDisplayer;
import com.sp.lib.common.image.drawable.RoundDrawable;

/**
 * Created by Lincoln on 15/7/22.
 */
public class ImageOptions {
    private static DisplayImageOptions.Builder getBuilder() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true);
    }

    public static DisplayImageOptions getStandard() {
        return getBuilder().build();
    }

    public static DisplayImageOptions getRound(int radius) {
        RoundDrawable drawable = new RoundDrawable(Color.GRAY, radius);
        return getBuilder()
                .displayer(new RoundDisplayer(radius))
                .showImageOnFail(drawable)
                .showImageForEmptyUri(drawable)
                .showImageOnLoading(drawable)
                .build();
    }

    public static DisplayImageOptions getRoundCorner(int cornerPixels) {
        ColorDrawable drawable = new ColorDrawable(Color.GRAY);

        return getBuilder()
                .showImageOnFail(drawable)
                .showImageForEmptyUri(drawable)
                .showImageOnLoading(drawable)
                .displayer(new RoundedBitmapDisplayer(cornerPixels))
                .build();
    }


}
