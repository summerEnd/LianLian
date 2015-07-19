package com.lianlian;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by Lincoln on 15/7/16.
 */
public class ImageOptions {
    public static DisplayImageOptions getOpt() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }
}
