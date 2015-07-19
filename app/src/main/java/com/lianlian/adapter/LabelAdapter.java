package com.lianlian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lianlian.R;
import com.lianlian.entity.Label;

import java.util.List;

/**
 * Created by Lincoln on 15/7/12.
 * 标签
 */
public class LabelAdapter
        extends BaseAdapter<Label> {


    public LabelAdapter(Context context, List<Label> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LabelHolder(getInflater().inflate(R.layout.label, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LabelHolder lbHolder = (LabelHolder) holder;
        lbHolder.button.setText(getData().get(position).name);
    }

    private class LabelHolder extends RecyclerView.ViewHolder {
        Button button;

        public LabelHolder(View itemView) {
            super(itemView);
            button = (Button) itemView;
        }
    }
}
