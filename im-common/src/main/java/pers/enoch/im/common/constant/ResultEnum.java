package pers.enoch.im.common.constant;
import lombok.Getter;

/**
 * @Author yang.zhao
 * @Date 2020/12/3 14:46
 * @Version 1.0
 * @Description 返回结果的枚举类
 **/
@Getter
public enum  ResultEnum {
    /**
     * 心跳检测
     */
    HEART_BEAT(0,"心跳检测"),
    /**
     * 成功状态码
     */
    SUCCESS(1, "成功"),

    /**
     * 服务器错误
     */
    SERVER_ERROR(0,"服务器内部错误,请重试"),

    /**
     * 参数错误
     */
    PARAM_IS_INVALID(1001,"参数无效"),

    PARAM_IS_BLANK(1002,"参数为空"),

    PARAM_TYPE_BIND_ERROR(1003,"参数类型错误"),

    PARAM_NOT_COMPLETE(1004,"参数类型缺失"),

    PHONE_VALID_ERROR(1005,"电话号码格式错误"),

    CODE_VALID_EXPIRED(1006,"验证码失效"),

    CODE_VALID_ERROR(1007,"验证码错误"),

    /**
     * 用户错误
     */
    USER_NOT_LOGGED_IN(2001,"用户未登录，访问路径需要验证，请登录"),

    USER_LOGIN_ERROR(2002,"账号不存在或者密码错误"),

    USER_ACCOUNT_FORBIDDEN(2003,"账号已被禁用"),

    USER_NOT_EXIST(2004,"用户不存在"),

    USER_HAS_EXISTED(2005,"用户已存在"),

    USER_OTHER_LOGIN(2006,"该账户其他地方登录！如果非本人操作请及时修改密码"),

    USER_TOKEN_EXPIRE(2007,"用户TOKEN已过期，请重新登录"),

    /**
     * 短信服务
     */
    SMS_SEND_OK(3001, "发送短信成功"),

    SMS_SEND_ERROR(3002,"发送短信失败"),

    /**
     * 用户状态
     */
    USER_LOGIN_SUCCESS(4001,"用户登录成功"),

    USER_REGISTE_SUCCESS(4002,"用户注册成功")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }


}
