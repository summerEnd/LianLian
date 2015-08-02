package com.lianlian.ui.main.me.edit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.lianlian.R;

public class MyselfEditorSPFitnessCreate  extends Activity {
	
	private Context context;
	
	/**
	 * 返回键
	 */
	private ImageView backBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself_editor_sportsvenues_fitnessclubcreate);
		
		context = this;
		initViews();
		
	}
	
	private void initViews(){
		
		backBtn = (ImageView) findViewById(R.id.backbtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	

}
