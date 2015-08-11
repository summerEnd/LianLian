package com.lianlian.http;

import com.lianlian.AppDelegate;
import com.sp.lib.common.support.net.client.SRequest;

/**
 * Created by Lincoln on 15/8/5.
 */
public class UserRequest extends SRequest {
    public UserRequest(String url) {
        super(url);
        put("userid", AppDelegate.getInstance().getUserInfo().id);
    }

    public UserRequest() {
        this("");
    }
}
