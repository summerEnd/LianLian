package com.lianlian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lianlian.AppDelegate;
import com.lianlian.R;
import com.lianlian.entity.Tag;
import com.lianlian.entity.UserInfo;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.HttpResponse;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.main.MainActivity;
import com.sp.lib.common.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;

public class AddInfoActivity3 extends AddInfoActivity2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView labelName = (TextView) findViewById(R.id.labelName);
        labelName.setText(R.string.sure);
        Button button= (Button) findViewById(R.id.btn_sure);
        button.setText(R.string.sure);
    }

    @Override
    protected void onUserInfoUpdated() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
