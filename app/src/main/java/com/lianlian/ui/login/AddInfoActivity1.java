package com.lianlian.ui.login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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

import com.lianlian.AppDelegate;
import com.lianlian.R;
import com.lianlian.entity.UserInfo;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.HttpResponse;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.widget.PictureWindow;
import com.lianlian.utils.ImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sp.lib.common.util.ImageUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddInfoActivity1 extends BaseActivity implements RadioGroup.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener {

    private ImageView image;
    private EditText editnick;
    private RadioButton boy;
    private RadioButton girl;
    private RadioGroup radioGroup;
    private Button btnnext;
    private TextView birthDayPicker;
    PictureWindow window;
    DatePickerDialog datePickerDialog;
    private String sex;
    private String imageUrl;

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

        initData();
    }

    void initData() {
        UserInfo userInfo = AppDelegate.getInstance().getUserInfo();
        int radius = getResources().getDimensionPixelSize(R.dimen.avatarSize)/2;
        ImageLoader.getInstance().displayImage(userInfo.img, image, ImageOptions.getRound(radius));
        birthDayPicker.setText(userInfo.birthday);
        editnick.setText(userInfo.nickname);
        if ("2".equals(userInfo.sex)){
            girl.setChecked(true);
        }else{
            boy.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_next: {
                final String birthDay = birthDayPicker.getText().toString();
                final String nick = editnick.getText().toString();

                final UserInfo addedInfo = new UserInfo();
                addedInfo.nickname = nick;
                addedInfo.birthday = birthDay;
                addedInfo.sex = sex;
                addedInfo.img = imageUrl;
                addedInfo.sid = AppDelegate.getInstance().getUserInfo().sid;

                HttpManager.updateUserInfo(addedInfo, new HttpHandler() {
                    @Override
                    public void onJsonResponseOk(HttpResponse response) throws JSONException {
                        super.onJsonResponseOk(response);
                        AppDelegate.getInstance().getUserInfo().append(addedInfo);
                        startActivity(new Intent(AddInfoActivity1.this, AddInfoActivity2.class));
                    }

                });
                break;
            }
            case R.id.image: {
                if (window == null) {
                    window = new MyPicturePickWindow(this);
                }
                window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            }
            case R.id.birthDayPicker: {
                if (datePickerDialog == null) {
                    datePickerDialog = new DatePickerDialog(this, R.style.Theme_AppCompat_Dialog, this, 1990, 0, 1);
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
        sex = checkedId == R.id.girl ? "2" : "1";
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, monthOfYear, dayOfMonth);
        birthDayPicker.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.getTime()));
    }

    private class MyPicturePickWindow extends PictureWindow {

        public MyPicturePickWindow(Activity context) {
            super(context);
        }

        @Override
        protected void onImageFileLoaded(Bitmap bitmap, File file) {
            HttpManager.uploadImage(file, new HttpHandler() {
                @Override
                public void onJsonResponseOk(HttpResponse response) throws JSONException {
                    super.onJsonResponseOk(response);
                    imageUrl = new JSONObject(response.data).optString("img");
                }
            });
            image.setImageBitmap(ImageUtil.roundBitmap(bitmap, getResources().getDimensionPixelSize(R.dimen.avatarSize)));
        }
    }
}
