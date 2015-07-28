package com.lianlian.ui.other;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MessageItemSlideLayout extends FrameLayout {

	private float mMaxSlideDistance = 150;

	private ValueAnimator mAnimator;

	private View mSlideView;

	private float mFlingTranslationX = 0;

	private boolean mAutoSlideOpen = true;

	public MessageItemSlideLayout(Context context) {
		this(context, null);
	}

	public MessageItemSlideLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MessageItemSlideLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		setClickable(true);
		mAnimator = new ValueAnimator();
		mAnimator.setIntValues(0, 100);
		mAnimator.setDuration(200);
		mAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (mSlideView == null) {
					return;
				}
				int currentValue = (Integer) animation.getAnimatedValue();
				float distance = 0;
				if (mAutoSlideOpen) {
					distance = mMaxSlideDistance * -1 - mFlingTranslationX;
				} else {
					distance = Math.abs(mFlingTranslationX);

				}
				if (distance == 0) {
					return;
				}
				mSlideView.setTranslationX(mFlingTranslationX + distance
						* currentValue / 100);

			}
		});
	}

	public void setMaxSlideDistance(float d) {
		mMaxSlideDistance = d;
	}

	public void setSlideView(int id) {
		mSlideView = findViewById(id);
	}

	public void setSlideView(View v) {
		mSlideView = v;
	}

	private float mDownX = 0;
	private float mDownTranslationX = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("debug", "MotionEvent.ACTION :" + event.getAction() );
		if (mSlideView != null) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d("debug", "MotionEvent.ACTION_DOWN");
				mDownX = event.getX();
				mDownTranslationX = mSlideView.getTranslationX();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d("debug", "MotionEvent.ACTION_MOVE");
				float distance = mDownTranslationX + event.getX() - mDownX;
				if (distance > 0) {
					distance = 0;
				}
				if (distance < mMaxSlideDistance * -1) {
					distance = mMaxSlideDistance * -1;
				}
				mSlideView.setTranslationX(distance);
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_OUTSIDE:
			case MotionEvent.ACTION_CANCEL:
				if (Math.abs(mSlideView.getTranslationX()) > mMaxSlideDistance / 2) {
					slideOpen();
				} else {
					slideClose();
				}
				break;
			}
		}
		return super.onTouchEvent(event);
	}

	private void slideOpen() {
		mAutoSlideOpen = true;
		mAnimator.cancel();
		mFlingTranslationX = mSlideView.getTranslationX();
		mAnimator.start();
	}

	private void slideClose() {
		mAutoSlideOpen = false;
		mAnimator.cancel();
		mFlingTranslationX = mSlideView.getTranslationX();
		mAnimator.start();
	}
}
