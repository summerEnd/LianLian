package com.lianlian.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lianlian.R;
import com.lianlian.http.HttpHandler;
import com.lianlian.http.HttpInterface;
import com.lianlian.http.HttpManager;
import com.lianlian.http.UserRequest;
import com.lianlian.ui.BaseActivity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackActivity extends BaseActivity {

    private EditText editContent;
    private Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);
        initialize();
    }

    private void initialize() {

        editContent = (EditText) findViewById(R.id.editContent);
        commit = (Button) findViewById(R.id.commit);

        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commit: {
                UserRequest request = new UserRequest(HttpInterface.FEED_BACK);
                request.put("content", editContent.getText().toString());
                HttpManager.getInstance().post(this, request, new HttpHandler() {
                    @Override
                    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {

                    }
                });
                break;
            }
        }
    }
}
