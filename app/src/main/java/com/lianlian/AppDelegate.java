package com.lianlian;

import com.lianlian.entity.UserInfo;
import com.sp.lib.SApplication;


public class AppDelegate extends SApplication {

    private static AppDelegate Instances;
    private UserInfo mUserInfo;

    public static AppDelegate getInstance() {
        return Instances;
    }

    public UserInfo getUserInfo(){
        if (mUserInfo==null){
            mUserInfo=new UserInfo();
        }
        return mUserInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instances = this;
        setDebug(BuildConfig.DEBUG);
    }
}
