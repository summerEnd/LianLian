package com.lianlian.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lianlian.R;

public class ModifyPasswordActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_password_layout);
	}

	public void toResetPassword(View v) {
		Intent intent = new Intent(this, ResetPasswordAcrivity.class);
		startActivity(intent);
	}
}
