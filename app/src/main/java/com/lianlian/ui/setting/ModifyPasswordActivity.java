package com.lianlian.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lianlian.AppDelegate;
import com.lianlian.R;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.sp.lib.common.util.ContextUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class ModifyPasswordActivity extends BaseActivity {

    private EditText rawPassword;
    private EditText newPassword;
    private EditText repeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_password_layout);
        initialize();
    }


    private void initialize() {

        rawPassword = (EditText) findViewById(R.id.rawPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        repeatPassword = (EditText) findViewById(R.id.repeatPassword);

        findViewById(R.id.forgetPsw).setOnClickListener(this);
        findViewById(R.id.btn_yes).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgetPsw: {
                Intent intent = new Intent(this, FindPasswordAcrivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_yes: {
                if (!isParamsOk()) {
                    return;
                }
                UserRequest request = new UserRequest(HttpInterface.EditPwd);
                request.put("phone", AppDelegate.getInstance().getUserInfo().mobilephone);
                request.put("oldpwd", rawPassword.getText().toString());
                request.put("newpwd", newPassword.getText().toString());
                HttpManager.getInstance().post(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {

                    }
                });
                break;
            }
        }
    }

    private boolean isParamsOk() {


        String newPwd = newPassword.getText().toString();
        String rpPwd = repeatPassword.getText().toString();
        String toastMsg = "";
        if (TextUtils.isEmpty(newPwd)) {
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
}
