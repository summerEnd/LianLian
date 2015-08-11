package com.lianlian.http;

/**
 * Created by Lincoln on 15/8/4.
 * http接口
 */
public class HttpInterface {
    private static final String HOST = "http://221.130.126.177:3000/lianlian/";

    /**
     * 注册发送短信接口
     * phone:手机号码
     * type:register（注册） bind（绑定）backpwd（找回密码）
     */
    public static final String MESSAGE = HOST + "Message";

    /**
     * 注册
     * phone表示手机号
     * code表示验证码
     * pwd表示密码
     */
    public static final String REGISTER = HOST + "Register";
    /**
     * 登录接口
     * username表示用户名
     * pwd表示密码
     */

    public static final String PHONE_LOGIN = HOST + "PhoneLogin";
    /**
     * 完善信息接口
     * Birthday：出生日期
     * nickname：昵称
     * userid：用户id
     * sex：1表示男 0表示女
     * type：文件格式 jpg，png
     * content：表示文件base64字符
     */
    public static final String ADD_INFO = HOST + "Perfect";

    /**
     * 绑定手机接口
     * phone手机号
     * code验证码
     * userid用户id
     */
    public static final String BIND_PHONE = HOST + "BindPhone";

    /**
     * 找回密码
     * phone手机号
     * code验证码
     * newpwd新密码
     */
    public static final String FIND_PSW = HOST + "BackPwd";

    /**
     * 提交反馈意见
     * userid表示用户id
     * content反馈意见
     */
    public static final String FEED_BACK = HOST + "FeedBack";

    /**
     * 1.形象标签上传接口：
     * userid表示用户id
     * image表示形象标签，多个用，隔开
     * <p/>
     * 2.职业圈：
     * userid表示用户id
     * occupation表示形象标签，多个用，隔开
     */
    public static final String LABEL = HOST + "Image";

    /**
     * 修改密码:
     * phone手机号
     * oldpwd旧密码
     * newpwd新密码
     */

    public static final String EditPwd = HOST + "EditPwd";

}
