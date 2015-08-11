package com.lianlian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.widget.TimerButton;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends BaseActivity {

    private EditText editphone;
    private EditText editcode;
    private TimerButton tvgetcode;
    private EditText editpsw;
    private Button register;
    private CheckBox cbprotocol;
    private TextView tvprotocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
    }

    private void initialize() {

        editphone = (EditText) findViewById(R.id.edit_phone);
        editcode = (EditText) findViewById(R.id.edit_code);
        tvgetcode = (TimerButton) findViewById(R.id.tv_get_code);
        editpsw = (EditText) findViewById(R.id.edit_psw);
        register = (Button) findViewById(R.id.register);
        cbprotocol = (CheckBox) findViewById(R.id.cb_protocol);
        tvprotocol = (TextView) findViewById(R.id.tv_protocol);

        initProtocol();
        tvgetcode.setCallback(new TimerButton.Callback() {
            @Override
            public String getTickerText() {
                return "获取验证码（%d“）";
            }

            @Override
            public int getMaxTime() {
                return 60;
            }
        });

        tvgetcode.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_code: {
                UserRequest request = new UserRequest(HttpInterface.MESSAGE);
                request.put("phone", editphone.getText().toString());
                request.put("type", "register");
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {
                        tvgetcode.start();
                        tvgetcode.setEnabled(false);
                    }
                });

                break;
            }
            case R.id.register: {

                if (!cbprotocol.isChecked()){
                    return;
                }

                UserRequest request = new UserRequest(HttpInterface.REGISTER);
                request.put("phone", editphone.getText().toString());
                request.put("code", editcode.getText().toString());
                request.put("pwd", editpsw.getText().toString());
                HttpManager.getInstance().get(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {
                        startActivity(new Intent(RegisterActivity.this, AddInfoActivity1.class));
                    }

                    @Override
                    public void onResultError(int statusCOde, Header[] headers, JSONObject response) throws JSONException {
                        super.onResultError(statusCOde, headers, response);
                        //todo test
                        startActivity(new Intent(RegisterActivity.this, AddInfoActivity1.class));
                    }
                });
                break;
            }
        }
    }

    private void initProtocol() {
        tvprotocol.setMovementMethod(LinkMovementMethod.getInstance());
        String text = tvprotocol.getText().toString();
        SpannableStringBuilder sb = new SpannableStringBuilder(text);
        int color = getResources().getColor(R.color.windowTitleColor);
        sb.setSpan(new ColorClickableSpan(color) {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(RegisterActivity.this, ProtocolActivity.class));
            }
        }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvprotocol.setText(sb);
    }

    public abstract class ColorClickableSpan extends ClickableSpan {
        int color;

        public ColorClickableSpan(int color) {
            this.color = color;
        }


        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(color);
            ds.linkColor = color;
            ds.setUnderlineText(true);
        }
    }

}
