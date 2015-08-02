package com.lianlian.ui.main.me.edit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.lianlian.R;

public class MyselfEditorTravelCreate extends Activity {
	
	Context context;
	/**
	 * 返回按钮
	 */
	private ImageView backBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself_editor_travel_create);
		
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
