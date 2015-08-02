package com.lianlian.ui.main.me.edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.lianlian.R;
import com.lianlian.adapter.FitnessClubListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运动地点主画面
 * @author Administrator
 *
 */
public class MyselfEditorSPFitness  extends Activity {
	
private Context context;
	
	private FitnessClubListViewAdapter fcvAdapter;
	
	/**
	 * 俱乐部listview
	 */
	private ListView fcvListView;
	
	/**
	 * 返回键
	 */
	private ImageView backBtn;
	
	private ImageView createBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself_editor_sportsvenues_fitnessclub);
		
		context = this;
		initViews();
		
	}
	
	private void initViews(){
		
		fcvListView = (ListView) findViewById(R.id.myself_editor_sportsvenues_fc_listview);
		
		fcvAdapter = new FitnessClubListViewAdapter(context, initData());
		fcvListView.setAdapter(fcvAdapter);
		
		backBtn = (ImageView) findViewById(R.id.backbtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		
		createBtn = (ImageView) findViewById(R.id.myself_editor_sv_fc_create);
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditorSPFitness.this, MyselfEditorSPFitnessCreate.class);
				startActivity(intent);
			}
		});
	}
	
	private List<Map<String, Object>> initData(){
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "美菲特健身俱乐部");
		map1.put("content", "");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "亚历山大健身俱乐部");
		map2.put("content", "");
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("title", "浩沙健身中心");
		map3.put("content", "");
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("title", "青鸟健身");
		map4.put("content", "");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0; i<3; i++){
			list.add(map1);
			list.add(map2);
			list.add(map3);
			list.add(map4);
		}
		
		return list;
		
	}
}
