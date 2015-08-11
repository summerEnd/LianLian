package com.lianlian.ui.main.me.edit;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.widget.ImageGridView;
import com.lianlian.ui.widget.PictureWindow;
import com.lianlian.ui.widget.SquareImageView;
import com.sp.lib.widget.AutoLayout;

public class MyselfEditor extends BaseActivity {

    PictureWindow window;
    private ImageGridView imageGridView;
    private ImageView initialImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself_editor);

        initViews();
    }

    private void initViews() {
        imageGridView = (ImageGridView) findViewById(R.id.gridLayout);
        initialImage = (ImageView) imageGridView.findViewById(R.id.initialImage);
        initialImage.setOnClickListener(onImageAddListener);
        //		photolay = (LinearLayout) findViewById(R.id.myself_editor_photolay);
        //		DisplayMetrics metric = new DisplayMetrics();
        //        getWindowManager().getDefaultDisplay().getMetrics(metric);
        //		int width = metric.widthPixels;
        //		int height = width/2*3;
        //		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(width, height);
        //		photolay.setLayoutParams(llp);

        //		recordBtn.setOnClickListener(new OnClickListener() {
        //
        //			@Override
        //			public void onClick(View view) {
        //				Intent intent = new Intent();
        //				intent.setClass(MyselfEditor.this, MyselfEditorRecord.class);
        //				startActivity(intent);
        //			}
        //		});
        //
        //		sportvenuesBtn = (ImageView) findViewById(R.id.myself_editor_sportvenues_btn);
        //		sportvenuesBtn.setOnClickListener(new OnClickListener() {
        //
        //			@Override
        //			public void onClick(View view) {
        //				Intent intent = new Intent();
        //				intent.setClass(MyselfEditor.this, MyselfEditorSPMain.class);
        //				startActivity(intent);
        //			}
        //		});
        //
        //		sportappealBtn = (ImageView) findViewById(R.id.myself_editor_sportappeal_btn);
        //		sportappealBtn.setOnClickListener(new OnClickListener() {
        //
        //			@Override
        //			public void onClick(View view) {
        //				Intent intent = new Intent();
        //				intent.setClass(MyselfEditor.this, MyselfEditorSportsApeal.class);
        //				startActivity(intent);
        //			}
        //		});
        //
        //		lovefoodBtn = (ImageView) findViewById(R.id.myself_editor_lovefood_btn);
        //		lovefoodBtn.setOnClickListener(new OnClickListener() {
        //
        //			@Override
        //			public void onClick(View view) {
        //				Intent intent = new Intent();
        //				intent.setClass(MyselfEditor.this, MyselfEditorLoveFood.class);
        //				startActivity(intent);
        //			}
        //		});
        //
        //		travelBtn = (ImageView) findViewById(R.id.myself_editor_travel_btn);
        //		travelBtn.setOnClickListener(new OnClickListener() {
        //
        //			@Override
        //			public void onClick(View view) {
        //				Intent intent = new Intent();
        //				intent.setClass(MyselfEditor.this, MyselfEditorTravel.class);
        //				startActivity(intent);
        //			}
        //		});
        //
        //		sportsBtn = (ImageView) findViewById(R.id.myself_editor_sports_btn);
        //		sportsBtn.setOnClickListener(new OnClickListener() {
        //
        //			@Override
        //			public void onClick(View view) {
        //				Intent intent = new Intent();
        //				intent.setClass(MyselfEditor.this, MyselfEditorSports.class);
        //				startActivity(intent);
        //			}
        //		});

    }

    private void addImage(Bitmap bitmap) {
        int count = imageGridView.getChildCount();
        ImageView lastChild = (ImageView) imageGridView.getChildAt(count - 1);
        if (lastChild != null) {
            lastChild.setOnClickListener(null);
            lastChild.setImageBitmap(bitmap);
        }

        ImageView imageView = new ImageView(this);
        ImageGridView.LayoutParams layoutParams = new ImageGridView.LayoutParams(0, 0);
        int column = 0;
        int row = 0;
        if (count == 1) {
            column = 2;
        }

        if (count == 2) {
            column = 2;
            row = 1;
        }

        if (count >= 3) {
            column = count % 3;
            row = (count) / 3 + 1;
        }
        layoutParams.specColumn(column, 1);
        layoutParams.specRow(row, 1);
        imageView.setOnClickListener(onImageAddListener);
        imageView.setImageResource(R.drawable.person6);
        imageGridView.addView(imageView, layoutParams);
    }

    private View.OnClickListener onImageAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (window == null) {
                window = new PictureWindow(MyselfEditor.this) {
                    @Override
                    protected void onImageLoaded(Bitmap bitmap) {
                        super.onImageLoaded(bitmap);
                        addImage(bitmap);
                    }
                };
            }
            window.show(v);
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (window != null) {
            window.onActivityResult(requestCode, resultCode, data);
        }
    }
}