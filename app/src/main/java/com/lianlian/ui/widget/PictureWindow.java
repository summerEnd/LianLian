package com.lianlian.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.lianlian.R;
import com.sp.lib.common.util.ImageUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lincoln on 15/8/11.
 */
public class PictureWindow extends PopupWindow implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 101;
    private static final int CAPTURE_IMAGE = 102;
    private static final int CROP_IMAGE = 103;
    Activity activity;
    File capturedImage;

    public PictureWindow(Activity context) {
        super(context);
        this.activity = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.picture_select_layout, null);
        setContentView(contentView);

        contentView.findViewById(R.id.openAlbum).setOnClickListener(this);

        contentView.findViewById(R.id.openCamera).setOnClickListener(this);

        setWindowLayoutMode(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
    }

    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.openAlbum: {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            }
            case R.id.openCamera: {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                capturedImage = new File(
                        Environment.getExternalStorageDirectory(),
                        getFileName());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(capturedImage));
                activity.startActivityForResult(intent, CAPTURE_IMAGE);
                break;
            }
        }
    }

    /**
     * 剪裁图片
     */
    private void cropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        //        intent.putExtra("output", cameraUri);// 保存到原文件
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, CROP_IMAGE);
    }

    @NonNull
    private String getFileName() {
        String time = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());
        return "lianlian" + time + ".png";
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();

            cropImage(selectedImage);


        } else if (requestCode == CAPTURE_IMAGE) {
            if (capturedImage == null || !capturedImage.exists()) {
                return;
            }

            cropImage(Uri.fromFile(capturedImage));
        } else if (requestCode == CROP_IMAGE && null != data) {
            Bitmap bitmap = data.getExtras().getParcelable("data");
            onImageLoaded(bitmap);
        }
    }

    protected void onImageLoaded(Bitmap bitmap) {

    }

    public void show(View view){
        showAtLocation(view, Gravity.BOTTOM,0,0);
    }
}
