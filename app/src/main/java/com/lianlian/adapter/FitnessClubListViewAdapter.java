package com.lianlian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lianlian.R;
import com.lianlian.ui.main.me.edit.MyselfEditorSPMain;

import java.util.List;
import java.util.Map;

public class FitnessClubListViewAdapter extends BaseAdapter {

	private LayoutInflater listContainer;// 视图容器
	private List<Map<String, Object>> mData;
	private Context context;
	private MyselfEditorSPMain meSpMain;

	public FitnessClubListViewAdapter(Context context) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context);
	}

	public FitnessClubListViewAdapter(Context context,
									  List<Map<String, Object>> data) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context);
		this.mData = data;
	}

	// 自定义控件集合
	static class ListItemView {

		// 名称
		private TextView title;
		// content
		private TextView content;
		// select
		private ImageView selImageView;
		//布局文件
		private LinearLayout layout;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 自定义视图
		ListItemView listItemView = null;

		Map<String, Object> item = mData.get(position);

		if (convertView == null) {

			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(
					R.layout.myself_editor_sportsvenues_listview, null);
			listItemView = new ListItemView();
			listItemView.layout = (LinearLayout) convertView.findViewById(R.id.myself_editor_sv_fc_ly);
			listItemView.title = (TextView) convertView.findViewById(R.id.myself_editor_sv_fc_title);
			listItemView.title.setText(item.get("title").toString());
			listItemView.content = (TextView) convertView.findViewById(R.id.myself_editor_sv_fc_content);
			listItemView.content.setText("33大v 120练友在此");
			listItemView.selImageView = (ImageView) convertView.findViewById(R.id.myself_editor_sv_fc_sel);
			listItemView.selImageView.setTag(R.drawable.sign_unselected);
			listItemView.selImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					ImageView iv = (ImageView) view;
					switch (getDrawableId(iv)) {
					case R.drawable.sign_selected:
						iv.setImageResource(R.drawable.sign_unselected);
						iv.setTag(R.drawable.sign_unselected);
						break;

					case R.drawable.sign_unselected:
						iv.setImageResource(R.drawable.sign_selected);
						iv.setTag(R.drawable.sign_selected);
						break;

					default:
						iv.setImageResource(R.drawable.sign_unselected);
						iv.setTag(R.drawable.sign_unselected);
						break;
					}
				}
			});
			
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		return convertView;
	}
	
	private int getDrawableId(ImageView iv) {
	    return (Integer) iv.getTag();
	}
}
