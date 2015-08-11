package com.lianlian.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.lianlian.R;

import java.util.Vector;

public class ConstellationChoiceDialog extends Dialog {

	Context context;
	
	/**
	 * 保存dialog对象
	 */
	private ConstellationChoiceDialog mDialog;

	/**
	 * 数据源
	 */
	Vector<String> data = new Vector<String>();
	
	/**
	 * 完成按钮
	 */
	private TextView confirmbtn;
	
	/**
	 * 组成滑动抓取器的6个textview
	 */
	private TextView choiceTextView1,choiceTextView2,choiceTextView3,choiceTextView4,choiceTextView5,choiceTextView6;
	
	public ConstellationChoiceDialog(Context context) {
		super(context);
		this.context = context;
	}

	public ConstellationChoiceDialog(Context context, int theme,
			Vector<String> data) {
		super(context, theme);
		this.context = context;
		this.data.addAll(data);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.myself_editor_constellation_dialog);
		mDialog = this;
		initView();
	}

	private void initView() {

	}

}
