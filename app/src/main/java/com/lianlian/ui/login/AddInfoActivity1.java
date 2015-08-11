package com.lianlian.ui.login;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.widget.PictureWindow;
import com.sp.lib.common.util.ImageUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddInfoActivity1 extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private ImageView image;
    private EditText editnick;
    private RadioButton boy;
    private RadioButton girl;
    private RadioGroup radioGroup;
    private Button btnnext;
    private TextView birthDayPicker;
    PictureWindow window;
    DatePickerDialog datePickerDialog;
    private Bitmap mBitmap;//图片
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_activity1);
        initialize();
    }


    private void initialize() {

        image = (ImageView) findViewById(R.id.image);
        birthDayPicker = (TextView) findViewById(R.id.birthDayPicker);
        editnick = (EditText) findViewById(R.id.edit_nick);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnnext = (Button) findViewById(R.id.btn_next);
        btnnext.setOnClickListener(this);
        birthDayPicker.setOnClickListener(this);
        image.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_next: {
                UserRequest request = new UserRequest(HttpInterface.ADD_INFO);
                request.put("Birthday", birthDayPicker.getText().toString());
                request.put("nickname", editnick.getText().toString());
                request.put("sex", sex);
                request.put("type", "jpeg");
                request.put("content", mBitmap == null ? "" : ImageUtil.base64Encode(mBitmap));
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {
                        startActivity(new Intent(AddInfoActivity1.this, AddInfoActivity2.class));
                    }
                });
                break;
            }
            case R.id.image: {
                if (window == null) {
                    window = new PictureWindow(AddInfoActivity1.this) {
                        @Override
                        protected void onImageLoaded(Bitmap bitmap) {
                            mBitmap = bitmap;
                            image.setImageBitmap(ImageUtil.roundBitmap(mBitmap, getResources().getDimensionPixelSize(R.dimen.avatarSize)));
                        }
                    };
                }
                window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            }
            case R.id.birthDayPicker: {
                if (datePickerDialog == null) {
                    datePickerDialog = new DatePickerDialog(this, R.style.Theme_AppCompat_Dialog, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar cal = Calendar.getInstance();
                            cal.set(year, monthOfYear, dayOfMonth);
                            birthDayPicker.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.getTime()));
                        }
                    }, 1990, 0, 1);
                }
                datePickerDialog.show();
                break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (window != null) {
            window.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        sex = checkedId == R.id.grid ? "0" : "1";
    }


}
