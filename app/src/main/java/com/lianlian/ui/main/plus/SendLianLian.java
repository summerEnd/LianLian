package com.lianlian.ui.main.plus;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.widget.RecordView;

public class SendLianLian extends BaseActivity {

    private ImageView addImage;
    private EditText editContent;
    private TextView textNumber;
    private TextView recordTime;
    private RecordView recordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_lian_lian);
        initialize();
    }


    private void initialize() {

        addImage = (ImageView) findViewById(R.id.addImage);
        editContent = (EditText) findViewById(R.id.editContent);
        textNumber = (TextView) findViewById(R.id.textNumber);
        recordTime = (TextView) findViewById(R.id.recordTime);
        recordView = (RecordView) findViewById(R.id.recordView);
    }
}
