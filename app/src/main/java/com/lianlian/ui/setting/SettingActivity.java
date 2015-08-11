package com.lianlian.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;

public class SettingActivity extends BaseActivity {

	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
		mIntent = new Intent();
	}

	public void toPrivacySetting(View v) {
		mIntent.setClass(this, PrivacySettingActivity.class);
		startActivity(mIntent);
	}

	public void toNotifySetting(View v) {
		mIntent.setClass(this, NotificationSettingActivity.class);
		startActivity(mIntent);
	}

	public void toModifyPassword(View v) {
		mIntent.setClass(this, ModifyPasswordActivity.class);
		startActivity(mIntent);
	}

	public void toReadClause(View v) {
		mIntent.setClass(this, ClauseActivity.class);
		startActivity(mIntent);
	}

	public void toFeedback(View v) {
		mIntent.setClass(this, FeedbackActivity.class);
		startActivity(mIntent);
	}
}
