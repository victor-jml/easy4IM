package pers.enoch.im.api.service;

/**
 * @Author yang.zhao
 * 2020/12/21
 * 短信服务
 **/
public interface SmsService {

    /**
     * 发送短信验证码
     * @param mobile
     */
    void sendSmsCode(String mobile);

    /**
     * 验证短信验证码
     * @param mobile
     * @param code
     * @return
     */
    boolean verifyCode(String mobile,String code);
}
