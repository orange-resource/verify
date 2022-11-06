package com.orange.verify.api.common.response;

import lombok.Getter;

/**
 * code码
 * 通用成功码 200
 * 基础操作码 1 - 999
 * 提醒 3000 - 4999
 * 失败 5000 - 7999
 */
@Getter
public enum RspCode {

    // 基础操作码
    SUCCESS(200, "操作成功"),
    QUERY_SUCCESS(200, "查询成功"),
    EXIST(200, "存在"),

    ERROR(400, "操作失败"),
    QUERY_ERROR(400, "查询失败"),
    NOT_EXIST(400, "不存在"),

    VERIFICATION_FAILED(401, "验证失败，没有足够的权限进行访问"),
    LOGIN_EXPIRED(401, "凭证已过期，请重新登录"),
    NOT_FOUND(404, "未找到相关资源页面"),

    INTERFACE_CLOSED(405, "未找到指定资源"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(405, "请求错误，未找到指定资源"),

    PARAMETER_ERROR(406, "参数不合法"),
    SUBMISSION_AGREEMENT_ERROR(406, "协议不合法"),

    CONTENT_IS_TOO_LARGE(413, "提交内容过大"),

    SYSTEM_ERROR(500, "系统错误"),
    UNKNOWN(500, "服务器发生了一个未知错误"),

    // 成功返回
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "登出成功"),
    REGISTER_SUCCESS(200, "注册成功"),

    // 提醒
    USER_NOT_EXIST_WARN(3000, "用户不存在"),
    LOGIN_PASSWORD_WARN(3001, "密码错误"),
    SMS_CODE_NOT_FOUND_WARN(3003, "验证码不正确"),
    REGISTER_NAME_ALREADY_EXIST_WARN(3006, "名称已被注册过了"),
    REGISTER_USERNAME_ALREADY_EXIST_WARN(3007, "用户名已被注册过了"),
    PRODUCTION_TOO_MUCH_WARN(3010,"生产过多充值卡密"),
    SOFT_CLOSE_WARN(3012,"软件关闭开放使用"),
    ACCOUNT_BLACKLIST_WARN(3018,"此账号已被加入黑名单！"),
    SOFT_NO_CHANGE_WARN(3020,"软件不支持换绑机器"),
    REGISTER_CLOSE_WARN(3022,"注册关闭"),
    VERSION_NOT_EXIST_WARN(3024,"版本不存在"),
    KEY_NOT_EXIST_WARN(3026,"钥匙为空，请重启软件重试！"),
    CARD_NOT_EXIST_WARN(3028,"卡密不存在"),
    CARD_USE_WARN(3030,"卡密已被使用"),
    CARD_CLOSURE_WARN(3032,"卡密已被封停"),
    CARD_PAST_DUE_WARN(3034,"卡密已过期"),
    ACCOUNT_ALREADY_EXIST_WARN(3036,"用户名已存在"),
    PASSWORD_LENGTH_WARN(3038,"密码长度是5到10位哟！"),
    SOFT_NOT_EXIST_WARN(3040,"软件不存在"),
    SOFT_INCONSISTENCY_WARN(3042,"卡密使用绑定软件不一致"),
    ACCOUNT_NOT_BOUND_CARD_WARN(3044,"账号还未绑定卡密，请先充值再使用！"),
    VC_NOT_EXIST_WARN(3046,"验证码过期了，请重新获取验证码"),
    VC_MISMATCHES_WARN(3048,"验证码输入错误"),
    SOFT_FREE_WARN(3050,"软件是免费的，无需绑定卡密"),
    DO_NOT_SUPPORT_REPLACEMENT_WARN(3052, "本软件不支持换机器进行使用"),

    // 错误
    REGISTER_ERROR(5001,"注册失败"),
    LOGIN_ERROR(5004,"登陆失败"),
    KEY_ERROR(5006,"服务器钥匙错误"),
    BAIDU_API_ERROR(5008,"IP错误"),
    UPDATE_PASSWORD_ERROR(5010,"改密失败"),
    LOGOUT_ERROR(5012,"登出失败"),
    SECURITY_CODE_ERROR(5014,"安全码错误！"),
    BINDING_CARD_ERROR(5016,"绑定卡密失败"),
    PASSWORD_ERROR(5018,"密码错误"),
    BINDING_CODE_ERROR(5020,"绑定机器失败"),
    LEAVE_MESSAGE_SEND_ERROR(5022,"留言发送失败"),
    ;

    private int code;
    private String message;

    RspCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
