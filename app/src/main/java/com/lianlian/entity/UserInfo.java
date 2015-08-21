package com.lianlian.entity;

import java.lang.reflect.Field;

/**
 * Created by Lincoln on 15/8/5.
 */
public class UserInfo {

    public String user_id;
    public String user_name;
    public String nickname;
    public String email;
    public String sex;
    public String birthday;
    public String rank;
    public String mobilephone;
    public String img;
    public String voice;
    public String longitude;
    public String latitude;
    public String constellation;
    public String province;
    public String city;
    public String district;
    public String address;
    public String active_range;
    public String circle;
    public String appearance;
    public String signature;
    public String sports_venues;
    public String movement_tag;
    public String eat_tag;
    public String travel_tag;
    public String allow_search;
    public String sid;

    public void append(UserInfo other){

        Field[] declaredFields = UserInfo.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                Object newValue = declaredField.get(other);
                if (newValue!=null){
                    declaredField.set(this,newValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
