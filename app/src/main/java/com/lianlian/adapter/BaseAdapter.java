package com.lianlian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by Lincoln on 15/7/12.
 * adapter基类
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    LayoutInflater inflater;
    private List<T> data;


    public BaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;

    }

    public LayoutInflater getInflater() {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater;
    }

    public Context getContext() {
        return context;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
