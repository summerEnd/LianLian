package com.lianlian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.main.MainActivity;

public class LoginActivity extends BaseActivity {

    private ImageView ivweixin;
    private ImageView ivqq;
    private ImageView ivweibo;
    private EditText editphone;
    private EditText editpsw;
    private Button login;
    private TextView forgetPsw;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        setContentView(R.layout.activity_login);
        initialize();
    }


    private void initialize() {

        ivweixin = (ImageView) findViewById(R.id.iv_weixin);
        ivqq = (ImageView) findViewById(R.id.iv_qq);
        ivweibo = (ImageView) findViewById(R.id.iv_weibo);
        editphone = (EditText) findViewById(R.id.edit_phone);
        editpsw = (EditText) findViewById(R.id.edit_psw);
        login = (Button) findViewById(R.id.login);
        forgetPsw = (TextView) findViewById(R.id.forgetPsw);
        register = (TextView) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPsw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login: {
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case R.id.register: {
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            }
            case R.id.forgetPsw: {
                break;
            }
        }
    }
}
