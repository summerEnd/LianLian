package com.lianlian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;
import com.lianlian.entity.Search;

import java.util.List;

/**
 * Created by Lincoln on 15/7/21.
 */
public class SearchAdapter extends BaseAdapter<Search> {
    public SearchAdapter(Context context, List<Search> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHolder(getInflater().inflate(R.layout.search_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    private class SearchHolder extends RecyclerView.ViewHolder {
        public SearchHolder(View itemView) {
            super(itemView);
        }
    }
}
