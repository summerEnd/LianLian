package com.lianlian.ui.main.me.edit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lianlian.R;

public class MyselfEditorRecord  extends Activity {
	
	private Context context;
	
	/**
	 * 重录按钮
	 */
	private ImageView recordBtn;
	
	/**
	 * 麦克风按钮
	 */
	private ImageView micropBtn;
	
	/**
	 * 播放按钮
	 */
	private ImageView playBtn;
	
	/**
	 * 上传按钮
	 */
	private ImageView uploadBtn;
	
	/**
	 * 时间提示
	 */
	private TextView timeTv;
	
	/**
	 * 返回键
	 */
	private ImageView backBtn;
	
	/**
	 * 记录当前状态
	 */
	private int microStatus = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself_editor_record);
		
		context = this;
		initViews();
		
	}
	
	private void initViews(){
		
		recordBtn = (ImageView) findViewById(R.id.myself_editor_record_recordBtn);
		recordBtn.setVisibility(View.GONE);
		recordBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(context, "重新录音!", Toast.LENGTH_LONG).show();
				microStatus = 1;
				micropBtn.setImageResource(R.drawable.microphone_pink_recording);
				timeTv.setVisibility(View.VISIBLE);
				recordBtn.setVisibility(View.GONE);
				playBtn.setVisibility(View.GONE);
				uploadBtn.setVisibility(View.GONE);
			}
		});
		
		micropBtn = (ImageView) findViewById(R.id.myself_editor_record_micropBtn);
		micropBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				if(microStatus == 0){
					microStatus = 1;
					micropBtn.setImageResource(R.drawable.microphone_pink_recording);
					Toast.makeText(context, "开始录音", Toast.LENGTH_LONG).show();
					timeTv.setVisibility(View.VISIBLE);
					uploadBtn.setVisibility(View.GONE);
				}else if(microStatus == 1){
					microStatus = 2;
					micropBtn.setImageResource(R.drawable.microphone_grey);
					timeTv.setVisibility(View.GONE);
					recordBtn.setVisibility(View.VISIBLE);
					playBtn.setVisibility(View.VISIBLE);
					uploadBtn.setVisibility(View.VISIBLE);
				}else if (microStatus == 2) {
					microStatus = 0;
					restoreView();
				}
				
			}
		});
		
		
		playBtn = (ImageView) findViewById(R.id.myself_editor_record_playBtn);
		playBtn.setVisibility(View.GONE);
		playBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(context, "播放!", Toast.LENGTH_LONG).show();
			}
		});
		
		uploadBtn = (ImageView) findViewById(R.id.myself_editor_record_uploadBtn);
		uploadBtn.setVisibility(View.GONE);
		uploadBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(context, "上传!", Toast.LENGTH_LONG).show();
			}
		});
		
		timeTv = (TextView) findViewById(R.id.myself_editor_record_timeTv);
		timeTv.setVisibility(View.GONE);
		
		backBtn = (ImageView) findViewById(R.id.backbtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	
	private void restoreView(){
		
		recordBtn.setVisibility(View.GONE);
		playBtn.setVisibility(View.GONE);
		uploadBtn.setVisibility(View.GONE);
		timeTv.setVisibility(View.GONE);
		micropBtn.setImageResource(R.drawable.microphone_pink);
	}

}
