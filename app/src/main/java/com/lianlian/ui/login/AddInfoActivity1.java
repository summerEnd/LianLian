package com.lianlian.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.sp.lib.activity.album.PhotoAlbumActivity;
import com.sp.lib.common.support.IntentFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddInfoActivity1 extends BaseActivity {

    private ImageView image;
    private EditText editnick;
    private RadioButton boy;
    private RadioButton girl;
    private RadioGroup radioGroup;
    private Button btnnext;
    PictureWindow window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_activity1);
        initialize();
    }


    private void initialize() {

        image = (ImageView) findViewById(R.id.image);
        editnick = (EditText) findViewById(R.id.edit_nick);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnnext = (Button) findViewById(R.id.btn_next);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddInfoActivity1.this, AddInfoActivity2.class));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window == null) {
                    window = new PictureWindow(AddInfoActivity1.this) {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                                case R.id.openAlbum: {
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("image/*");
                                    startActivity(intent);
                                    break;
                                }
                                case R.id.openCamera: {
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                                    startActivityForResult(intent, 1);
                                    break;
                                }
                            }
                        }
                    };
                }
                window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private static class PictureWindow extends PopupWindow implements View.OnClickListener {

        public PictureWindow(Context context) {
            super(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View contentView = inflater.inflate(R.layout.picture_select_layout, null);
            setContentView(contentView);

            contentView.findViewById(R.id.openAlbum).setOnClickListener(this);

            contentView.findViewById(R.id.openCamera).setOnClickListener(this);

            setWindowLayoutMode(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            setFocusable(true);
        }

        public void onClick(View v) {
        }


    }
}
