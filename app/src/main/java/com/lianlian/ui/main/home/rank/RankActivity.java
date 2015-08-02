package com.lianlian.ui.main.home.rank;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.lianlian.utils.ImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sp.lib.widget.list.grid.Utils;
import com.sp.lib.widget.list.grid.model.AsymmetricItem;
import com.sp.lib.widget.list.grid.widget.AsymmetricGridView;
import com.sp.lib.widget.list.grid.widget.AsymmetricGridViewAdapter;

/**
 * Created by Lincoln on 15/7/21.
 */
public class RankActivity extends BaseActivity {
    private AsymmetricGridView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initialize();
    }

    private void initialize() {

        listView = (AsymmetricGridView) findViewById(R.id.listView);
        listView.setRequestedColumnCount(3);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
        listView.setAdapter(new AsymmetricGridViewAdapter<Item>(this, listView, new MyAdapter()));
        listView.setDebugging(true);
        listView.setRequestedHorizontalSpacing(2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public static class Item implements AsymmetricItem {
        private int columnSpan;
        private int rowSpan;
        private int position;

        public Item() {
            this(1, 1, 0);
        }

        public Item(int columnSpan, int rowSpan, int position) {
            this.columnSpan = columnSpan;
            this.rowSpan = rowSpan;
            this.position = position;
        }

        public Item(Parcel in) {
            readFromParcel(in);
        }

        @Override
        public int getColumnSpan() {
            return columnSpan;
        }

        @Override
        public int getRowSpan() {
            return rowSpan;
        }

        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return String.format("%s: %sx%s", position, rowSpan, columnSpan);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        private void readFromParcel(Parcel in) {
            columnSpan = in.readInt();
            rowSpan = in.readInt();
            position = in.readInt();
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeInt(columnSpan);
            dest.writeInt(rowSpan);
            dest.writeInt(position);
        }

        /* Parcelable interface implementation */
        public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

            @Override
            public Item createFromParcel(@NonNull Parcel in) {
                return new Item(in);
            }

            @Override
            @NonNull
            public Item[] newArray(int size) {
                return new Item[size];
            }
        };
    }

    private class MyAdapter extends BaseAdapter {

        Item big;
        Item small;


        public MyAdapter() {
            big = new Item(2, 2, 0);
            small = new Item(1, 1, 0);
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return position == 0 ? new Item(2, 2, 0) : new Item(1, 1, position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(RankActivity.this).inflate(R.layout.rank_item, parent, false);
                holder.ivimage = (ImageView) convertView.findViewById(R.id.iv_image);
                holder.tvname = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tvinfo = (TextView) convertView.findViewById(R.id.tv_info);
                holder.tvlabel1 = (TextView) convertView.findViewById(R.id.tv_label1);
                holder.tvlabel2 = (TextView) convertView.findViewById(R.id.tv_label2);
                holder.contentLayout = (FrameLayout) convertView.findViewById(R.id.contentLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ImageLoader.getInstance().
                    displayImage("http://h.hiphotos.baidu.com/image/pic/item/023b5bb5c9ea15ce090cc38db5003af33b87b257.jpg"
                            , holder.ivimage, ImageOptions.getStandard());
            return convertView;
        }


    }

    private class ViewHolder {
        private ImageView ivimage;
        private TextView tvname;
        private TextView tvinfo;
        private TextView tvlabel1;
        private TextView tvlabel2;
        private FrameLayout contentLayout;

    }
}
