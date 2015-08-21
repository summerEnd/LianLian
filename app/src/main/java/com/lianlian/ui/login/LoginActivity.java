package com.lianlian.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.lianlian.http.HttpResponse;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.main.MainActivity;
import com.lianlian.ui.setting.FindPasswordAcrivity;
import com.sp.lib.common.support.net.client.SRequest;
import com.sp.lib.common.util.ContextUtil;
import com.sp.lib.common.util.JsonUtil;
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

import org.json.JSONException;

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

        UMWXHandler wxHandler = new UMWXHandler(LoginActivity.this, ApiConfig.WE_CHAT_APP_ID, ApiConfig.WE_CHAT_APP_SECRET);

        wxHandler.addToSocialSDK();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login: {

                UserRequest request = new UserRequest(HttpInterface.LOGIN);
                final String phone = editphone.getText().toString();
                final String pwd = editpsw.getText().toString();

                request.put("mobilephone", phone);
                request.put("password", pwd);
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onJsonResponseOk(HttpResponse response) throws JSONException {
                        super.onJsonResponseOk(response);

                        getSharedPreferences("user", MODE_PRIVATE).edit()
                                .putString("username", phone)
                                .putString("password", pwd)
                                .apply();
                        loginWith(JsonUtil.get(response.data, UserInfo.class));

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
                thirdLogin(SHARE_MEDIA.QQ);
                break;
            }
            case R.id.iv_weixin: {
                thirdLogin(SHARE_MEDIA.WEIXIN);
                break;
            }
            case R.id.iv_weibo: {
                thirdLogin(SHARE_MEDIA.SINA);

                break;
            }
        }
    }

    private void thirdLogin(final SHARE_MEDIA platform) {

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
                String access_token = value.getString("access_token");
                if (!TextUtils.isEmpty(uid)) {
                    mController.getPlatformInfo(LoginActivity.this, platform, new GetPlatformListener(platform, uid, access_token));
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
     * 登录系统
     *
     * @param userInfo
     */
    void loginWith(@NonNull UserInfo userInfo) {
        AppDelegate.getInstance().setUserInfo(userInfo);
//        startActivity(new Intent(LoginActivity.this, AddInfoActivity1.class));
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }


    private class GetPlatformListener implements SocializeListeners.UMDataListener {
        private SHARE_MEDIA platform;
        private String uid;
        private String access_token;

        public GetPlatformListener(SHARE_MEDIA platform, String uid, String access_token) {
            this.platform = platform;
            this.uid = uid;
            this.access_token = access_token;
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(int status, Map<String, Object> info) {

            if (status == StatusCode.ST_CODE_SUCCESSED && info != null) {
                StringBuilder sb = new StringBuilder();
                Set<String> keys = info.keySet();
                for (String key : keys) {
                    sb.append(key).append("=").append(info.get(key).toString()).append("\r\n");
                }
                Log.d("TestData", sb.toString());

                try {

                    String openid = uid;
                    String type = "";

                    switch (platform) {
                        case WEIXIN: {
                            type = "1";
                            Object unionid = info.get("unionid");
                            if (unionid != null) {
                                openid = unionid.toString();
                            }

                            break;
                        }
                        case QQ:
                        case QZONE: {
                            type = "2";
                            break;
                        }

                        case SINA: {
                            type = "3";
                            break;
                        }
                    }


                    SRequest request = new SRequest(HttpInterface.THIRD_LOGIN);
                    request.put("access_token", access_token);
                    request.put("openid", openid);
                    request.put("type", type);
                    HttpManager.getInstance().post(LoginActivity.this, request, new HttpHandler() {
                        @Override
                        public void onJsonResponseOk(HttpResponse response) throws JSONException {
                            loginWith(JsonUtil.get(response.data, UserInfo.class));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ContextUtil.toast("授权失败！");
                }
            }
        }
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
