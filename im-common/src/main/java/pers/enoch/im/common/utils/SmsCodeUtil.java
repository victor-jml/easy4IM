package pers.enoch.im.common.utils;

import cn.hutool.core.util.RandomUtil;
import pers.enoch.im.common.constant.SmsLengthEnum;

/**
 * @Author yang.zhao
 * 2020/12/21
 * 短信验证码工具类
 **/
public class SmsCodeUtil {
    /**
     * 返回一个指定长度的短信验证码
     * @param smsLengthEnum
     * @return
     */
    public static String createSmsRandomCode(SmsLengthEnum smsLengthEnum){
        return RandomUtil.randomNumbers(smsLengthEnum.getLength());
    }

}
