package com.lianlian.adapter.chumo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.adapter.BaseAdapter;
import com.lianlian.entity.Chumo;
import com.lianlian.ui.test.DATA;
import com.lianlian.ui.widget.HomeItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Lincoln on 15/8/12.
 */
public class ChumoGridAdapter extends BaseAdapter<Chumo> {
    public ChumoGridAdapter(Context context, List<Chumo> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeItem itemView = new HomeItem(getContext());
        itemView.tvname.setText("adasd");

        return new ChumoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChumoHolder chumoHolder = (ChumoHolder) holder;
        ImageLoader.getInstance().displayImage(DATA.SQUARE_IMAGE, chumoHolder.homeItem.ivimage);

    }

    private class ChumoHolder extends RecyclerView.ViewHolder {
        HomeItem homeItem;

        public ChumoHolder(HomeItem itemView) {
            super(itemView);
            homeItem=itemView;
        }
    }
}
