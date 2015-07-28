package com.lianlian.ui.other;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianlian.R;

public class OptionsDialog extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_FRAME, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setCanceledOnTouchOutside(true);
		View v = inflater.inflate(R.layout.option_dialog_layout, null);
		return v;
	}
}
