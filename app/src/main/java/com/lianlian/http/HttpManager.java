package com.lianlian.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.lianlian.AppDelegate;
import com.lianlian.entity.UserInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.ResponseHandlerInterface;
import com.sp.lib.common.support.net.client.SRequest;
import com.sp.lib.common.util.SLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

/**
 * 应用的所有Http请求原则上应该都经过这个类发起
 */
public class HttpManager {
    private static HttpManager instance;
    private AsyncHttpClient client = new AsyncHttpClient();


    public static HttpManager getInstance() {
        if (instance == null) {
            //做一些初始化操作
            instance = new HttpManager();
            AsyncHttpClient client = instance.getClient();
            client.setTimeout(30000);
        }
        return instance;
    }

    public AsyncHttpClient getClient() {
        return client;
    }

    /**
     * @param requestHandle 要处理的请求
     * @return true 已经取消
     */
    public boolean cancel(RequestHandle requestHandle) {
        return !(requestHandle != null && !requestHandle.isCancelled() && !requestHandle.isFinished()) || requestHandle.cancel(true);
    }

    /**
     * 发送get请求
     */
    public RequestHandle get(Context context, SRequest request, ResponseHandlerInterface handler) {
        debug(request);

        if (context == null) {
            context = AppDelegate.getInstance();
        }
        String url = request.getUrl();
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return client.get(context, url, request, handler);
    }

    /**
     * 发送get请求
     */
    public RequestHandle post(Context context, SRequest request, ResponseHandlerInterface handler) {
        debug(request);

        if (context == null) {
            context = AppDelegate.getInstance();
        }
        String url = request.getUrl();
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return client.post(context, url, request, handler);
    }

    //修复url
    private void debug(SRequest request) {

        SLog.debug(request.toLogString());
    }

    public static void uploadImage(File bitmap, HttpHandler httpHandler) {
        UserRequest request = new UserRequest(HttpInterface.UPLOAD_IMAGE);
        try {
            request.put("photo", bitmap);
            HttpManager.getInstance().post(AppDelegate.getInstance(), request, httpHandler);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserInfo(UserInfo info, HttpHandler httpHandler) {
        UserRequest request = new UserRequest(HttpInterface.UPDATE_INFO);

        Field[] declaredFields = UserInfo.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                request.put(declaredField.getName(), declaredField.get(info));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        HttpManager.getInstance().post(AppDelegate.getInstance(), request, httpHandler);
    }
}
