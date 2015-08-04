package com.lianlian.http;

import android.app.Dialog;
import android.text.TextUtils;

import com.sp.lib.common.support.net.client.SHttpProgressHandler;
import com.sp.lib.common.util.ContextUtil;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public abstract class HttpHandler extends SHttpProgressHandler {

    private boolean showToast;

    public HttpHandler() {
        this(true);
    }

    /**
     * @param showToast true 用toast弹出Response的message false 反之
     */
    public HttpHandler(boolean showToast) {
        this.showToast = showToast;
    }

    @Override
    public Dialog onCreateDialog() {
        return null;
    }

    @Override
    public final void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);

        String code = response.optString("code");
        if (showToast && !TextUtils.isEmpty(code)) {
            ContextUtil.toast(code);
        }

        String success = response.optString("success");
        try {
            if ("1".equals(success)) {
                onResultOk(statusCode, headers, response);
            } else {
                onResultError(statusCode, headers, response);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        super.onSuccess(statusCode, headers, response);
        throw new RuntimeException("wrong return type");
    }

    @Override
    public final void onSuccess(int statusCode, Header[] headers, String responseString) {
        super.onSuccess(statusCode, headers, responseString);
        throw new RuntimeException("wrong return type");
    }

    @Override
    public final void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        onException();
    }

    @Override
    public final void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        onException();
    }

    @Override
    public final void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        onException();
    }


    /**
     * 请求成功，并且Response的status为true时调用
     */
    public abstract void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException;

    /**
     * 请求成功，并且Response的status为false时调用。
     * 如：密码错误等等
     */
    public void onResultError(int statusCOde, Header[] headers, JSONObject response) throws JSONException {
    }

    /**
     * 请求失败，如网络链接不通或json格式不正确等
     */
    public void onException() {
        ContextUtil.toast("网络异常");
    }


}
