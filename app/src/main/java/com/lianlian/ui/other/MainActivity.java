package com.lianlian.ui.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lianlian.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void settingPage(View v) {
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
	}

	public void messagePage(View v) {
		Intent intent = new Intent(this, MessageActivity.class);
		startActivity(intent);
	}

}
