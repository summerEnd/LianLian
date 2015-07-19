package com.lianlian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianlian.HotActivity;
import com.lianlian.ImageOptions;
import com.lianlian.R;
import com.lianlian.entity.Meet;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sp.lib.common.util.DisplayUtil;

import java.util.List;

/**
 * Created by Lincoln on 15/7/16.
 */
public class MeetAdapter extends BaseAdapter<Meet> {

    int images[]=new int[]{R.drawable.h111,R.drawable.h222,R.drawable.h333,R.drawable.test_grid,R.drawable.h555,R.drawable.h666};
    public MeetAdapter(Context context, List<Meet> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeetHolder(getInflater().inflate(R.layout.item_grid_meet, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MeetHolder meetHolder = (MeetHolder) holder;
        meetHolder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    private class
            MeetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MeetHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams != null) {
                int width = DisplayUtil.getScreenWidth(itemView.getContext()) / 2-4;
                layoutParams.width = width;
                layoutParams.height= (int) (width*409/344f);
            }
            imageView.requestLayout();
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == 1) {
                v.getContext().startActivity(new Intent(v.getContext(), HotActivity.class));
            }
        }
    }
}
