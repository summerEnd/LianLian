package com.lianlian.ui.main.me.edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.lianlian.R;

/**
 * 运动地点主画面
 * @author Administrator
 *
 */
public class MyselfEditorSPMain  extends Activity {
	
private Context context;
	
	/**
	 * 家中选择按钮
	 */
	private ImageView homeselBtn;
	
	/**
	 * 酒店选择按钮
	 */
	private ImageView hotelselBtn;
	
	/**
	 * 返回键
	 */
	private ImageView backBtn;
	
	/**
	 * 运动场所挑选俱乐部按钮
	 */
	private ImageView fvcJumpBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself_editor_sportsvenues_main);
		
		context = this;
		initViews();
		
	}
	
	private void initViews(){
		
		homeselBtn = (ImageView) findViewById(R.id.myself_editor_sv_main_homesel);
		homeselBtn.setTag(R.drawable.sign_unselected);
		homeselBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
				ImageView iv = (ImageView) view;
				switch (getDrawableId(iv)) {
				case R.drawable.sign_selected:
					homeselBtn.setImageResource(R.drawable.sign_unselected);
					homeselBtn.setTag(R.drawable.sign_unselected);
					break;

				case R.drawable.sign_unselected:
					homeselBtn.setImageResource(R.drawable.sign_selected);
					homeselBtn.setTag(R.drawable.sign_selected);
					break;

				default:
					homeselBtn.setImageResource(R.drawable.sign_unselected);
					homeselBtn.setTag(R.drawable.sign_unselected);
					break;
				}
			}
		});
		
		hotelselBtn = (ImageView) findViewById(R.id.myself_editor_sv_main_hotelsel);
		hotelselBtn.setTag(R.drawable.sign_unselected);
		hotelselBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
				ImageView iv = (ImageView) view;
				switch (getDrawableId(iv)) {
				case R.drawable.sign_selected:
					hotelselBtn.setImageResource(R.drawable.sign_unselected);
					hotelselBtn.setTag(R.drawable.sign_unselected);
					break;

				case R.drawable.sign_unselected:
					hotelselBtn.setImageResource(R.drawable.sign_selected);
					hotelselBtn.setTag(R.drawable.sign_selected);
					break;

				default:
					hotelselBtn.setImageResource(R.drawable.sign_unselected);
					hotelselBtn.setTag(R.drawable.sign_unselected);
					break;
				}
			}
		});
		
		backBtn = (ImageView) findViewById(R.id.backbtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		
		fvcJumpBtn = (ImageView) findViewById(R.id.myself_editor_sv_main_fvcjump);
		fvcJumpBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(MyselfEditorSPMain.this, MyselfEditorSPFitness.class);
				startActivity(intent);
			}
		});
	}
	private int getDrawableId(ImageView iv) {
	    return (Integer) iv.getTag();
	}

}
