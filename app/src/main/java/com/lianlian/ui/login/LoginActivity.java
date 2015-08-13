package com.lianlian.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.AppDelegate;
import com.lianlian.R;
import com.lianlian.api.ApiConfig;
import com.lianlian.entity.UserInfo;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.main.MainActivity;
import com.lianlian.ui.setting.FindPasswordAcrivity;
import com.sp.lib.common.support.net.client.SRequest;
import com.sp.lib.common.util.ContextUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

public class LoginActivity extends BaseActivity {

    private ImageView ivweixin;
    private ImageView ivqq;
    private ImageView ivweibo;
    private EditText editphone;
    private EditText editpsw;
    private Button login;
    private TextView forgetPsw;
    private TextView register;
    private UMSocialService mController;//友盟

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

        ivqq.setOnClickListener(this);
        ivweixin.setOnClickListener(this);
        ivweibo.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        editphone.setText(sp.getString("username", ""));
        editpsw.setText(sp.getString("password", ""));

        initApi();
    }

    void initApi() {

        mController = UMServiceFactory.getUMSocialService("com.umeng.login");
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginActivity.this, ApiConfig.QQ_APP_ID, ApiConfig.QQ_KEY);
        qqSsoHandler.addToSocialSDK();

        mController.getConfig().setSsoHandler(new SinaSsoHandler());

//        UMWXHandler wxHandler = new UMWXHandler(LoginActivity.this, ApiConfig.WE_CHAT_APP_ID, ApiConfig.WE_CHAT_APP_SECRET);
//        wxHandler.addToSocialSDK();

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

                    @Override
                    public void onException() {
                        super.onException();
                        // TODO: 15/8/12 test
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
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
            case R.id.iv_qq: {
                login(SHARE_MEDIA.QQ);
                break;
            }
            case R.id.iv_weixin: {
                login(SHARE_MEDIA.WEIXIN);
                break;
            }
            case R.id.iv_weibo: {
                login(SHARE_MEDIA.SINA);

                break;
            }
        }
    }

    private void login(final SHARE_MEDIA platform) {

        mController.doOauthVerify(this, platform, new SocializeListeners.UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA platform) {
                //ToastUtil.show(LoginActivity.this, "start");
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                ContextUtil.toast("授权失败,请重新登录微信！");
                e.printStackTrace();


            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                String uid = value.getString("uid");
                if (!TextUtils.isEmpty(uid)) {
                    getUserInfo(platform, uid);
                } else {
                    ContextUtil.toast("授权失败！");
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
            }
        });
    }


    /**
     * 获取授权平台的用户信息</br>
     */
    private void getUserInfo(final SHARE_MEDIA platform, final String uid) {
        mController.getPlatformInfo(this, platform, new SocializeListeners.UMDataListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, Map<String, Object> info) {
                // String showText = "";
                // if (status == StatusCode.ST_CODE_SUCCESSED) {
                // showText = "用户名：" + info.get("screen_name").toString();
                // Log.d("#########", "##########" + info.toString());

                // } else {
                // showText = "获取用户信息失败";
                // }
                if (status == StatusCode.ST_CODE_SUCCESSED && info != null) {
                    StringBuilder sb = new StringBuilder();
                    Set<String> keys = info.keySet();
                    for (String key : keys) {
                        sb.append(key + "=" + info.get(key).toString() + "\r\n");
                    }
                    Log.d("TestData", sb.toString());

                    JSONObject userInfo = new JSONObject();
                    try {

                        String name = "";
                        String openid = "";
                        if (null != info.get("screen_name")) {
                            name = info.get("screen_name").toString();
                        } else if (null != info.get("nickname")) {
                            name = info.get("nickname").toString();
                        }

                        if (null != info.get("unionid") && platform.equals(SHARE_MEDIA.WEIXIN)) {
                            openid = info.get("unionid").toString();
                        } else {
                            openid = uid;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        ContextUtil.toast("授权失败！");
                    }
                } else {
                }
            }
        });
    }


    //如果有使用任一平台的SSO授权,则必须在对应的activity中实现onActivityResult方法,并添加如下代码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 根据requestCode获取对应的SsoHandler
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
                requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
