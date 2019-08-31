package com.rongdong.constant;

import com.alibaba.fastjson.JSONObject;

/**
 * 公共常量
 *
 * @author hsh
 * @create 2018-03-31 23:35
 **/
public class CommonConstants {

    public final static String APPLICATION_JSON = "application/json;charset=UTF-8";

    /**
     * 每条非认证用户信息扣除多少
     */
    public final static String USER_NO_AUTHEN = "USER_NO_AUTHEN";
    /**
     * 每条认证用户信息扣除多少
     */
    public final static String USER_AUTHEN = "USER_AUTHEN";

    /**
     * 渠道商权限代码 channel
     */
    public final static String ROLE_CHANNEL = "channel";
    /**
     * 渠道商权限代码 admin
     */
    public final static String ROLE_CHANNEL_ADMIN = "admin";




    /**
     * 错误码
     */
    public enum ErrorCode {
        ERROR_IP_BLOCKED(0, "您的IP被限制访问"),
        ERROE_ILLEGAL_ARGUMENT(1, "缺少参数或参数不合法"),
        ERROR_ACCOUNT_BLOCKED(2, "账号被锁定，请30分钟后尝试"),
        ERROR_ILLEGAL_PHONE(3, "请输入正确的手机号码"),
        ERROR_DETECT_HTMLINJECT(4, "发现HTML注入攻击"),
        ERROR_ILLEGAL_UUID(5, "UUID不合法"),
        ERROR_INTERNAL_ERROR(6, "服务器内部错误"),
        ERROR_SMS_CODE(7, "请输入正确的验证码"),
        ERROR_CAPTCHA_CODE(8, "图形验证码无效"),
        ERROR_NO_AUTHORITY(9, "帐号未登录"),
        ERROR_FORBIDDEN(10, "无权访问"),
        ERROR_PHONE_EXISTS(16, "账号已被注册"),
        ERROR_PHONE_NO_EXISTS(17, "账号不存在"),
        ERROR_OLD_PASSWORD(18, "原密码输入错误"),
        ERROR_CONFIRM_PASSWORD(19, "两次输入密码不一致"),
        ERROR_PASSWORD_ERROR(20, "账号或者密码错误，请重新输入"),
        ERROR_MAX_TRY_TIME(21, "操作过于频繁，手机号码被锁定，请于明天再试"),
        ERROR_OPERATOR_FAILED(22, "运营商发送失败"),
        ERROR_PHONE_SAME(23, "相同的手机号码"),
        ERROR_NO_DATA(24, "用户信息尚未设置"),
        ERROR_DETECT_INJECT(25, "发现SQL注入攻击"),
        ERROR_SERVER_MAINTENANCE(26, "服务器正在维护"),
        ERROR_SMS_CODE_LIMITED(27, "手机验证码超过校验次数"),
        ERROR_EMAIL_ERROR(32, "邮箱无效"),
        ERROR_EMAIL_EXISTS(33, "邮箱已被绑定"),
        ERROR_SMS_CODE_TYPE(34, "手机验证码类型无效"),
        ERROR_SMS_CODE_RESEND(35, "验证码已失效，请重新获取"),
        ERROR_SMS_CODE_FAILED(36, "短信发送失败"),
        ERROR_SMS_FUNCTION_CLOSED(37, "短信功能已关闭，暂无法收到短信"),
        ERROR_DETECT_XSSINJECT(38, "发现XSS攻击"),
        ERROR_MAX_VERIFY_BLOCKED(39, "账号被锁定，请30分钟后尝试"),
        ERROR_OAUTHUID_EXISTS(40, "该第三方平台用户已经绑定"),
        ERROR_OAUTH_REJECTED(41, "未授权访问该第三方平台"),
        ERROR_OAUTHTOKEN_ILLEGAL(42, "该平台OPENID非法"),
        ERROR_ACCOUNT_BLACK_USER(43, "黑名单用户"),
        ERROR_USER_REFERER(44, "推荐码无效"),
        ERROR_PAY_PASSWORD_ERROR(45, "支付密码错误"),
        ERROR_SIGN_VERFIY_FAIL(46, "签名校验失败"),
        ERROR_OPERAT_FAIL(47, "操作失败"),
        ERROR_SERVICE_IN_REST(48, "服务器跑月球了,等飞回来再说"),
        ERROR_DATA_EXIST(49, "数据已存在"),
        ERROR_DATA_NO_EXIST(50, "数据不存在"),
        ERROR_OPERAT_RECHARGE_FAIL(51, "充值失败，请联系管理员"),

        ERROR_TEMP(00, "临时变量");

        private Integer code;
        private String description;
        private String tempDesc;

        public String getTempDesc() {
            return this.tempDesc;
        }

        public void setTempDesc(String tempDesc) {
            this.tempDesc = tempDesc;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getDescription() {
            return this.description;
        }

        ErrorCode(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public CommonConstants.ErrorCode customDescription(String customError) {
            if (customError != null) {
                this.description = customError;
            }
            return this;
        }

        public String toString() {
            JSONObject json = new JSONObject();
            JSONObject error = new JSONObject();
            error.put("code", this.code);
            error.put("description", this.description);
            json.put("error", error);
            return json.toString();
        }
    }


    public static final class FilterContants {
        public static final int FILTER_MAINTENANCE = Integer.MIN_VALUE;
        public static final int FILTER_SQLSECURITY = Integer.MIN_VALUE + 10;
        public static final int FILTER_XSSSECURITY = Integer.MIN_VALUE + 20;
        public static final int FILTER_AUTHORIZATION_LOGIN = Integer.MIN_VALUE + 30;
        public static final int FILTER_AUTHORIZATION_PATH = Integer.MIN_VALUE + 40;
        public static final int FILTER_QUERY = Integer.MIN_VALUE + 50;
        public static final int FILTER_TICKER = Integer.MIN_VALUE + 60;
        public static final int FILTER_POWERED = Integer.MIN_VALUE + 70;
        public static final int FILTER_HEADER = Integer.MIN_VALUE + 80;
        public static final int FILTER_ECHO = Integer.MIN_VALUE + 90;
    }

}
