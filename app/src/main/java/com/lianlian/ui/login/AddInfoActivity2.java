package com.lianlian.ui.login;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.lianlian.AppDelegate;
import com.lianlian.R;
import com.lianlian.adapter.LabelAdapter;
import com.lianlian.adapter.decor.PaddingDecoration;
import com.lianlian.entity.Label;
import com.lianlian.entity.Tag;
import com.lianlian.entity.UserInfo;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.HttpResponse;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;
import com.sp.lib.common.util.JsonUtil;
import com.sp.lib.common.util.RandomUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AddInfoActivity2 extends BaseActivity {

    private RecyclerView gridLayout;
    private LabelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_activity2);
        initialize();
    }

    private void initialize() {

        gridLayout = (RecyclerView) findViewById(R.id.gridLayout);
        GridLayoutManager layout = new GridLayoutManager(this, 4);
        gridLayout.setLayoutManager(layout);

        findViewById(R.id.btn_sure).setOnClickListener(this);

        adapter = new LabelAdapter(this, new ArrayList<Tag>());
        gridLayout.setAdapter(adapter);
        gridLayout.addItemDecoration(new PaddingDecoration(5, 5, 5, 5));
        getTagList();
    }

    void getTagList() {
        UserRequest request = new UserRequest(HttpInterface.GET_TAG_LIST);
        request.put("cat_id", "1");
        HttpManager.getInstance().post(this, request, new HttpHandler() {
            @Override
            public void onJsonResponseOk(HttpResponse response) throws JSONException {
                JsonUtil.getArray(new JSONArray(response.data), Tag.class, adapter.getData());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sure: {
                final UserInfo info = new UserInfo();
                info.circle = adapter.getCheckedLabel();
                HttpManager.updateUserInfo(info, new HttpHandler() {
                    @Override
                    public void onJsonResponseOk(HttpResponse response) throws JSONException {
                        AppDelegate.getInstance().getUserInfo().append(info);
                        onUserInfoUpdated();
                    }
                });
                break;
            }
        }
    }

    protected void onUserInfoUpdated(){
        startActivity(new Intent(AddInfoActivity2.this, AddInfoActivity3.class));
    }
}
