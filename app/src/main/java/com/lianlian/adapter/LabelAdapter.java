package com.lianlian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;

import com.lianlian.R;
import com.lianlian.entity.Label;
import com.lianlian.entity.Tag;

import java.util.List;

/**
 * Created by Lincoln on 15/7/12.
 * 标签
 */
public class LabelAdapter
        extends BaseAdapter<Tag> {

    private CheckBox checked;

    public LabelAdapter(Context context, List<Tag> data) {
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

    public String getCheckedLabel() {
        return checked == null ? null : checked.getText().toString();
    }

    private class LabelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox button;

        public LabelHolder(View itemView) {
            super(itemView);
            button = (CheckBox) itemView;
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            checked.setChecked(false);
            checked = button;
        }
    }
}
