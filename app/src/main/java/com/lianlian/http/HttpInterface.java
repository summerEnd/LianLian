package com.lianlian.http;

/**
 * Created by Lincoln on 15/8/4.
 * http接口
 */
public class HttpInterface {
    private static final String HOST = "http://lianlian.tanwang.com.cn/Home/";

    /**
     * 图片根目录
     */
    private static final String IMAGE_ROOT = "http://lianlian.tanwang.com.cn/";

    /**
     * mobilephone : 手机号
     * mac : mac地址(选填）
     * device : 手机型号（选填）
     * <p/>
     * 返回参数
     * 发送成功
     * mobilephone : 手机号
     * 发送失败及对应代码：
     * 300 => '当前设备所在网络的IP短信发送条数超过限制',
     * 301 => '当前手机号码发送条数超过限制',
     * 302 => '短信验证码不正确',
     * 303 => '短信网关发送失败',
     * 304 => '接收短信手机号不能为空'
     */
    public static final String SEND_SMS = HOST + "Common/sendSMS";

    /**
     * 上传图片
     * 提交参数
     * post方式进行提交，表单名称是photo
     * 支持格式：jpg, gif, png, jpeg
     * <p/>
     * 返回参数
     * 发送成功
     * img : 上传后，图片在远程服务器存储的地址
     */
    public static final String UPLOAD_IMAGE = HOST + "Common/uploadImage";
    /**
     * 提交参数
     * post方式进行提交，表单名称同样是photo
     * 支持格式：amr
     * <p/>
     * 返回参数
     * 发送成功
     * voice : 上传后，音频在远程服务器存储的地址
     */
    public static final String UPLOAD_AUDIO = HOST + "Common/uploadVoice";
    /**
     * 提交参数
     * 无
     * 返回参数
     * region_id : 地区id
     * parent_id ：父地区id
     * name ：地区名称
     * type ：地区类型（0：国家 1：省份 2：市 3：区|县）
     * child : 子地区
     */
    public static final String GET_REGION = HOST + "Common/region";

    /**
     * 提交参数
     * mobilephone ： 手机号码
     * password ： 密码
     * <p/>
     * 返回参数
     * user_id ： 用户uid
     * user_name ： 账号
     * nickname ： 昵称
     * email ： 邮箱
     * sex ： 性别 ( 0保密; 1男; 2女)
     * birthday ： 出生日期(格式：0000-00-00)
     * rank ： 用户等级（0：普通 1：加V 2:VIP)
     * mobilephone ： 手机号
     * img ： 头像地址
     * imgs : 附加图片，可存多张，以|分割
     * voice ： 声音介绍地址
     * longitude ： 经度
     * latitude ： 维度
     * constellation ： 星座
     * province ： 省
     * city ： 市
     * district ：县|区
     * address ： 详细地址
     * active_range ： 活动范围
     * circle ： 圈子
     * appearance ： 形象标签
     * signature ： 个性签名
     * sports_venues ： 运动场所
     * movement_tag ： 运动标签
     * eat_tag ： 爱吃标签
     * travel_tag ： 旅行标签
     * allow_search : 是否允许他人通过练恋号找到自己(0：不允许；1：允许)
     * sid ： session信息
     * login_type ： 登录方式：1普通登录 2第三方登录
     * <p/>
     * 备注
     * 在做登录操作时要操作两次，第一次登录本接口，第二次登录环信系统
     * 登录环信系统的账号是本接口获取的user_id,密码是用户输入的密码进行md5(32位小写)加密后的密码。
     * 具体流程是:
     * 1.用户输入账号密码登录本接口，登录成功后获取登录信息（包含user_id)
     * 2.凭借步骤1获取的user_id以及md5(用户输入密码)登录环信系统
     */
    public static final String LOGIN = HOST + "User/login";

    /**
     * 提交参数
     * mobilephone ： 手机号码
     * password ： 密码
     * code ： 验证码
     * <p/>
     * 返回参数
     * user_id ： 用户uid
     * mobilephone ： 手机号
     * sid ： session信息
     */
    public static final String REGISTER = HOST + "User/register";
    /**
     * 提交参数
     * user_id： 用户uid
     * sid： session信息
     * <p/>
     * 返回参数
     * user_id ： 用户uid
     * user_name ： 账号
     * nickname ： 昵称
     * email ： 邮箱
     * sex ： 性别 ( 0保密; 1男; 2女)
     * birthday ： 出生日期(格式：0000-00-00)
     * rank ： 用户等级（0：普通 1：加V 2:VIP)
     * mobilephone ： 手机号
     * img ： 头像地址
     * imgs : 附加图片，可存多张，以|分割
     * voice ： 声音介绍地址
     * longitude ： 经度
     * latitude ： 维度
     * constellation ： 星座
     * province ： 省(id)
     * city ： 市(id)
     * district ：县|区(id)
     * address ： 详细地址(字符串)
     * active_range ： 活动范围(字符串,以|分隔)
     * circle ： 圈子(字符串,以|分隔)
     * appearance ： 形象标签(字符串,以|分隔)
     * signature ： 个性签名(字符串,以|分隔)
     * sports_venues ： 运动场所(字符串,以|分隔)
     * movement_tag ： 运动标签(字符串,以|分隔)
     * eat_tag ： 爱吃标签(字符串,以|分隔)
     * travel_tag ： 旅行标签(字符串,以|分隔)
     * allow_search : 是否允许他人通过练恋号找到自己(0：不允许；1：允许)
     * sid ： session信息
     */
    public static final String GET_INFO = HOST + "User/getInfo";
    /**
     * 密码重置：
     * 提交参数
     * mobilephone ： 手机号码
     * password ： 密码
     * code ： 验证码
     * <p/>
     * 返回参数
     * mobilephone ： 手机号
     */
    public static final String RESET_PWD = HOST + "User/resetPassword";

    /**
     * 修改密码:
     * 提交参数
     * mobilephone ： 手机号码
     * oldpassword ： 原密码
     * newpassword ： 新密码
     * <p/>
     * 返回参数
     * mobilephone ： 手机号
     */
    public static final String EDIT_PWD = HOST + "User/editPassword";

    /**
     * user_id： 用户uid
     * sid： session信息
     * nickname ： 昵称(选填）
     * email ： 邮箱(选填）
     * sex ： 性别 ( 0保密; 1男; 2女)(选填）
     * birthday ： 出生日期(格式：0000-00-00)(选填）
     * img ： 头像地址(选填）
     * voice ： 声音介绍地址(选填）
     * longitude ： 经度(选填）
     * latitude ： 维度(选填）
     * constellation ： 星座(选填）
     * province ： 省(选填）
     * city ： 市(选填）
     * district ：县|区 (选填）
     * address ： 详细地址(选填）
     * active_range ： 活动范围(选填）
     * circle ： 圈子(选填）
     * appearance ： 形象标签(选填）
     * signature ： 个性签名(选填）
     * sports_venues ： 运动场所(选填）
     * movement_tag ： 运动标签(选填）
     * eat_tag ： 爱吃标签(选填）
     * travel_tag ： 旅行标签(选填）
     * allow_search : 是否允许他人通过练恋号找到自己(0：不允许；1：允许)(选填）
     */
    public static final String UPDATE_INFO = HOST + "User/updateInfo";

    /**
     * 说明：通过关键词搜索，获取用户列表
     * <p/>
     * 提交参数
     * keyword ： 关键词
     * <p/>
     * 返回参数
     * user_id = 用户UID
     * nickname = 昵称
     * img = 头像地址
     */
    public static final String SEARCH = HOST + "User/search";

    /**
     * 提交参数
     * access_token：第三方登录授权后获取的access_token
     * openid：用户在第三方平台的唯一标识(注意:在新浪中的叫法是UID,但向接口传递参数名称仍用openid这个参数名)
     * type：类型(1 :微信 2：QQ 3：新浪)
     * <p/>
     * 返回参数
     * user_id ： 用户uid
     * password : 密码
     * user_name ： 账号
     * nickname ： 昵称
     * email ： 邮箱
     * sex ： 性别 ( 0保密; 1男; 2女)
     * birthday ： 出生日期(格式：0000-00-00)
     * rank ： 用户等级（0：普通 1：加V 2:VIP)
     * mobilephone ： 手机号
     * img ： 头像地址
     * imgs : 附加图片，可存多张，以|分割
     * voice ： 声音介绍地址
     * longitude ： 经度
     * latitude ： 维度
     * constellation ： 星座
     * province ： 省
     * city ： 市
     * district ：县|区
     * address ： 详细地址
     * active_range ： 活动范围
     * circle ： 圈子
     * appearance ： 形象标签
     * signature ： 个性签名
     * sports_venues ： 运动场所
     * movement_tag ： 运动标签
     * eat_tag ： 爱吃标签
     * travel_tag ： 旅行标签
     * allow_search : 是否允许他人通过练恋号找到自己(0：不允许；1：允许)
     * sid ： session信息
     * is_first_login : 是否是第一次登录,0:表示否 1：表示是
     * login_type ： 登录方式：1普通登录 2第三方登录
     * <p/>
     * 注：当is_first_login=1时,返回的是上面参数中的部分参数
     * user_id ： 用户uid
     * password : 密码
     * sid ： session信息
     * nickname ： 昵称
     * img ： 头像地址
     * is_first_login : 是否是第一次登录,0:表示否 1：表示是
     * login_type ： 登录方式：1普通登录 2第三方登录
     * <p/>
     * 备注
     * 第三方登录时，在做登录操作时要操作两次，第一次登录本接口，第二次登录环信系统
     * 登录环信系统的账号是本接口获取的user_id,密码是本接口获取的password。
     * 具体流程是:
     * 1.用户输入账号密码登录本接口，登录成功后获取登录信息（包含user_id)
     * 2.凭借步骤1获取的user_id以及password登录环信系统
     */
    public static final String THIRD_LOGIN = HOST + "User/third_login";

    /**
     * 遇见用户列表
     * User/meetUserList
     * 提交参数
     * user_id： 用户uid
     * sid： session信息
     * count：每个分页显示的用户数量,该值范围1-50,默认10
     * page：分页
     * <p/>
     * 返回参数
     * user_id ： 用户uid
     * nickname ： 昵称
     * img ： 头像地址
     * voice ： 声音介绍地址
     * circle ： 圈子
     * longitude：经度
     * latitude：纬度
     */
    public static final String MEET_USER_LIST = HOST + "User/meetUserList";
    /**
     * cat_id=1：圈子
     * 2：形象
     * 3：运动
     * 4：爱吃
     * 5：旅行-国内
     * 6：旅行-国外
     * 7：运动诉求
     * 8：运动场所
     * 9：运动频率
     * 10：星座
     * 11：遇见短语
     * <p/>
     * 返回参数
     * tag_id ： 标签id
     * name : 标签名称
     * cat_id ： 标签分类
     * sort_order ： 排序
     */
    public static final String GET_TAG_LIST = HOST + "Tag/getTag";


    /**
     * 注册发送短信接口
     * phone:手机号码
     * type:register（注册） bind（绑定）backpwd（找回密码）
     */
    public static final String MESSAGE = HOST + "Message";


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
