package pers.enoch.im.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author yang.zhao
 * 2020/12/21
 * 短信位数
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SmsLengthEnum {
    /** 4位短信验证码 */
    SMS_LENGTH_4(4),
    /** 6位短信验证码 */
    SMS_LENGTH_6(6),

    ;

    private int length;
}
