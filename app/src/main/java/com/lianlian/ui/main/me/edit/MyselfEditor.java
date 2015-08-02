package com.lianlian.ui.main.me.edit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;

public class MyselfEditor extends BaseActivity {
	
	/**
	 * 录音跳转Btn
	 */
	private ImageView recordBtn;
	
	/**
	 * 照片layout
	 */
	private LinearLayout photolay;
	
	/**
	 * 运动场所按钮
	 */
	private ImageView sportvenuesBtn;
	
	/**
	 * 运动诉求按钮
	 */
	private ImageView sportappealBtn;
	
	/**
	 * 爱吃标签按钮
	 */
	private ImageView lovefoodBtn;
	
	/**
	 * 旅行标签按钮
	 */
	private ImageView travelBtn;
	
	/**
	 * 运动标签按钮
	 */
	private ImageView sportsBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myself_editor);
		
		initViews();
	}

	private void initViews(){
		
//		photolay = (LinearLayout) findViewById(R.id.myself_editor_photolay);
//		DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//		int width = metric.widthPixels;
//		int height = width/2*3;
//		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(width, height);
//		photolay.setLayoutParams(llp);
		
		recordBtn = (ImageView) findViewById(R.id.myself_editor_recording_btn);
		recordBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditor.this, MyselfEditorRecord.class);
				startActivity(intent);
			}
		});
		
		sportvenuesBtn = (ImageView) findViewById(R.id.myself_editor_sportvenues_btn);
		sportvenuesBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditor.this, MyselfEditorSPMain.class);
				startActivity(intent);
			}
		});
		
		sportappealBtn = (ImageView) findViewById(R.id.myself_editor_sportappeal_btn);
		sportappealBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditor.this, MyselfEditorSportsApeal.class);
				startActivity(intent);
			}
		});
		
		lovefoodBtn = (ImageView) findViewById(R.id.myself_editor_lovefood_btn);
		lovefoodBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditor.this, MyselfEditorLoveFood.class);
				startActivity(intent);
			}
		});
		
		travelBtn = (ImageView) findViewById(R.id.myself_editor_travel_btn);
		travelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditor.this, MyselfEditorTravel.class);
				startActivity(intent);
			}
		});
		
		sportsBtn = (ImageView) findViewById(R.id.myself_editor_sports_btn);
		sportsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditor.this, MyselfEditorSports.class);
				startActivity(intent);
			}
		});
		
	}
}