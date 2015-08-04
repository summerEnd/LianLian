package com.lianlian.http;

/**
 * Created by Lincoln on 15/8/4.
 * http接口
 */
public class HttpInterface {
    private static final String HOST = "221.130.126.177:3000/lianlian/";

    /**
     * 注册发送短信接口
     * phone:手机号码
     * type:register（注册） bind（绑定）backpwd（找回密码）
     */
    public static final String MESSAGE = "Message";

    /**
     * 注册
     * phone表示手机号
     * code表示验证码
     * pwd表示密码
     */
    public static final String REGISTER = "Register";
    /**
     * 登录接口
     * username表示用户名
     * pwd表示密码
     */

    public static final String PHONE_LOGIN = "PhoneLogin";
    /**
     * 完善信息接口
     * Birthday表示出生日期
     * nickname表示昵称
     * userid表示用户id
     */
    public static final String ADD_INFO = "Perfect";

    /**
     * 绑定手机接口
     * phone手机号
     * code验证码
     * userid用户id
     */
    public static final String BIND_PHONE = "BindPhone";
    /**
     * 找回密码
     * phone手机号
     * code验证码
     * newpwd新密码
     */
    public static final String FIND_PSW = "BackPwd";
    /**
     * 提交反馈意见
     * userid表示用户id
     * content反馈意见
     */
    public static final String FEED_BACK = "FeedBack";
    /**
     * 1.形象标签上传接口：
     * userid表示用户id
     * image表示形象标签，多个用，隔开
     * <p>
     * 2.职业圈：
     * userid表示用户id
     * occupation表示形象标签，多个用，隔开
     */
    public static final String LABEL = "Image";

    /**
     * 修改密码:
     * phone手机号
     * oldpwd旧密码
     * newold新密码
     */

    public static final String EditPwd = "EditPwd";

}
