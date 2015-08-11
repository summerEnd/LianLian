package com.lianlian.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.AppDelegate;
import com.lianlian.R;
import com.lianlian.entity.UserInfo;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.main.MainActivity;
import com.lianlian.ui.setting.FindPasswordAcrivity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

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

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        editphone.setText(sp.getString("username", ""));
        editpsw.setText(sp.getString("password", ""));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login: {

                UserRequest request = new UserRequest(HttpInterface.PHONE_LOGIN);
                final String phone = editphone.getText().toString();
                final String pwd = editpsw.getText().toString();

                request.put("username", phone);
                request.put("pwd", pwd);
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {
                        UserInfo userInfo = AppDelegate.getInstance().getUserInfo();
                        userInfo.id = response.getString("userid");
                        userInfo.phone = phone;
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        getSharedPreferences("user", MODE_PRIVATE).edit()
                                .putString("username", phone)
                                .putString("password", pwd)
                                .apply();
                    }
                });
                break;
            }
            case R.id.register: {
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            }
            case R.id.forgetPsw: {
                startActivity(new Intent(this, FindPasswordAcrivity.class));
                break;
            }
        }
    }
}
