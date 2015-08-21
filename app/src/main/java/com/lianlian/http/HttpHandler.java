package com.lianlian.http;

import android.app.Dialog;
import android.text.TextUtils;
import android.util.SparseArray;

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
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.code = response.optInt("code");
        httpResponse.desc = response.optString("desc");
        httpResponse.data = response.optString("data");

        if (showToast && httpResponse.code != 1) {
            String msg = messages.get(httpResponse.code);
            if (msg != null) {
                ContextUtil.toast(msg);
            }
        }

        try {
            if (httpResponse.code == 1) {
                //                onResultOk(statusCode, headers, response);
                onJsonResponseOk(httpResponse);
            } else {
                //                onResultError(statusCode, headers, response);
                onJsonResponseFailed(httpResponse);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onJsonResponseOk(HttpResponse response) throws JSONException {
    }

    public void onJsonResponseFailed(HttpResponse response) throws JSONException {
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
     *
     * @deprecated
     */
    public void onResultOk(int statusCode, Header[] headers, JSONObject response) throws JSONException {

    }

    /**
     * 请求成功，并且Response的status为false时调用。
     * 如：密码错误等等
     *
     * @deprecated
     */
    public void onResultError(int statusCOde, Header[] headers, JSONObject response) throws JSONException {
    }

    /**
     * 请求失败，如网络链接不通或json格式不正确等
     */
    public void onException() {
        ContextUtil.toast("网络异常");
    }

    SparseArray<String> messages = new SparseArray<>();

    {
        messages.put(200, "UID或SID不能为空");
        messages.put(201, "亲，您的SESSION无效");
        messages.put(202, "亲，您的SESSION已过期,请重新登录");
        messages.put(300, "当前设备所在网络的IP短信发送条数超过限制");
        messages.put(301, "当前手机号码发送条数超过限制");
        messages.put(302, "短信验证码不正确");
        messages.put(303, "短信网关发送失败");
        messages.put(304, "接收短信手机号不能为空");
        messages.put(305, "短信验证码已过期，请重新获取");
        messages.put(306, "短信验证码不能为空");
        messages.put(400, "用户账号密码不能为空");
        messages.put(401, "用户账号密码不正确");
        messages.put(402, "注册失败，该账号已存在");
        messages.put(403, "用户信息不存在");
        messages.put(404, "用户信息修改失败");
        messages.put(405, "未搜索到用户信息");
        messages.put(406, "用户信息未发生变化");
        messages.put(407, "access_token,openid,type不能为空");
        messages.put(408, "type不正确,只能为1,2,3");
        messages.put(409, "touid或content不能为空");
        messages.put(410, "无邀约信息");
        messages.put(500, "图片上传失败");
        messages.put(501, "音频上传失败");
        messages.put(600, "标签分类id不能为空");
        messages.put(700, "banner分类id不能为空");
        messages.put(800, "排行榜分类和类型不能为空");
        messages.put(900, "场地排列类型type必须是1,2,3");
        messages.put(901, "field_id不能为");
    }


}
