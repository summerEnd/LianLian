package com.lianlian.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lianlian.R;

public class NotificationSettingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notify_setting_layout);
	}

	public void toBindPhone(View v) {
		Intent intent = new Intent(this, BindPhoneActivity.class);
		startActivity(intent);
	}
}
