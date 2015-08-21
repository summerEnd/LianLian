package com.lianlian.ui.main.plus;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lianlian.R;


/**
 * 宝贝详情界面的弹窗
 * @author http://yecaoly.taobao.com
 *
 */
@SuppressLint("CommitPrefEdits")
public class BabyPopWindow implements OnDismissListener, OnClickListener {
	private TextView pop_choice_16g,pop_choice_32g,pop_choice_16m,pop_choice_32m,pop_choice_black,pop_choice_white,pop_add,pop_reduce,pop_num,pop_ok;
	private ImageView pop_del;
	
	private PopupWindow popupWindow;
	private OnItemClickListener listener;
	private final int ADDORREDUCE=1;
	private Context context;
	/**保存选择的颜色的数据*/
	private String str_color="";
	/**保存选择的类型的数据*/
	private String str_type="";
	
	private LinearLayout m_obj_paizhao = null;
	private LinearLayout m_obj_xiangce = null;
	
	private int gravity ;
	
	public BabyPopWindow(final Context context, int id, int gravity) {
		this.context=context;
		this.gravity = gravity;
		View view=LayoutInflater.from(context).inflate(id, null);
		
		
		
		
		popupWindow=new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//设置popwindow的动画效果
		popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
	
		m_obj_paizhao = (LinearLayout) view.findViewById(R.id.id_popuWnd_line2);
		m_obj_paizhao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "拍照", Toast.LENGTH_SHORT).show();
				if(null != m_obj_choiceLister){
					m_obj_choiceLister.paizhao();
				}
			}
		});
		
		
		m_obj_xiangce = (LinearLayout) view.findViewById(R.id.id_popuWnd_line3);
		m_obj_xiangce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "相册", Toast.LENGTH_SHORT).show();
				if(null != m_obj_choiceLister){
					m_obj_choiceLister.paizhao();
				}
			}
		});
	}
	
	
	
	
	public interface OnItemClickListener{
		/** 设置点击确认按钮时监听接口 */
		public void onClickOKPop();
	}

	/**设置监听*/
	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener=listener;
	}
	
	
	// 当popWindow消失时响应
	@Override
	public void onDismiss() {
		
	}
	
	/**弹窗显示的位置*/  
	public void showAsDropDown(View parent){
		popupWindow.showAtLocation(parent, this.gravity, 0, 0);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
	}
	
	/**消除弹窗*/
	public void dissmiss(){
		popupWindow.dismiss();
	}


	@Override
	public void onClick(View v) {

	}

	
	private PicChoice m_obj_choiceLister = null;
	public interface PicChoice{
		void paizhao();
		void xiangce();
	}
	
	public void registerPic(PicChoice lister){
		m_obj_choiceLister = lister;
	}
	
}
