package com.lianlian.ui.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lianlian.R;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.login.LoginActivity;
import com.lianlian.ui.widget.TimerButton;
import com.sp.lib.common.util.ContextUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class FindPasswordAcrivity extends BaseActivity implements TimerButton.Callback {

    private EditText editphone;
    private EditText editcode;
    private TimerButton timerbutton;
    private EditText newPassword;
    private EditText repeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_layout);
        initialize();
    }

    private void initialize() {

        editphone = (EditText) findViewById(R.id.edit_phone);
        editcode = (EditText) findViewById(R.id.edit_code);
        timerbutton = (TimerButton) findViewById(R.id.timer_button);
        newPassword = (EditText) findViewById(R.id.newPassword);
        repeatPassword = (EditText) findViewById(R.id.repeatPassword);

        timerbutton.setCallback(this);
        timerbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.timer_button: {
                UserRequest request = new UserRequest(HttpInterface.MESSAGE);
                request.put("phone", editphone.getText().toString());
                request.put("type", "backpwd");
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {
                        timerbutton.start();
                        timerbutton.setEnabled(false);
                    }
                });
                break;
            }
            case R.id.btn_yes: {

                if (!isParamsOk()) {
                    return;
                }

                UserRequest request = new UserRequest(HttpInterface.FIND_PSW);
                request.put("phone", editphone.getText().toString());
                request.put("code", editcode.getText().toString());
                request.put("newpwd", newPassword.getText().toString());
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FindPasswordAcrivity.this);
                        String msg;
                        try {
                            msg = response.getString("code");
                        } catch (JSONException e) {
                            msg = "密码修改成功！";
                        }
                        builder.setMessage(msg);

                        builder.setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(FindPasswordAcrivity.this, LoginActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }
                        });
                        builder.show();
                    }
                });

                break;
            }
        }
    }

    private boolean isParamsOk() {

        String newPwd = newPassword.getText().toString();
        String rpPwd = repeatPassword.getText().toString();
        String phone = editphone.getText().toString();
        String toastMsg = "";
        if (TextUtils.isEmpty(phone)) {
            toastMsg = "请输入手机号";
        } else if (TextUtils.isEmpty(newPwd)) {
            toastMsg = "请输入密码！";
        } else if (TextUtils.isEmpty(rpPwd)) {
            toastMsg = "请再次输入密码！";
        } else if (!TextUtils.equals(rpPwd, newPwd)) {
            toastMsg = "两次密码不一样！";
        }

        if (!TextUtils.isEmpty(toastMsg)) {
            ContextUtil.toast(toastMsg);
            return false;
        }
        return true;
    }

    @Override
    public String getTickerText() {
        return "获取验证码（%d“）";
    }

    @Override
    public int getMaxTime() {
        return 60;
    }


}
