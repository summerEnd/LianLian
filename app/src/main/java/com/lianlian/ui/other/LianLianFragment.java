package com.lianlian.ui.other;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lianlian.R;

public class LianLianFragment extends Fragment {

	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_layout, null);
		mListView = (ListView) v.findViewById(R.id.listView);
		mListView.setAdapter(mAdapter);
		return v;
	}

	private BaseAdapter mAdapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			MessageItemSlideLayout view = (MessageItemSlideLayout) inflater.inflate(R.layout.lianlian_list_item, null);
			view.setSlideView(R.id.front);
			float deleteButtonWidth = getResources().getDimension(R.dimen.delete_button_width);
			view.setMaxSlideDistance(deleteButtonWidth);
			convertView = view;
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

	};
}
