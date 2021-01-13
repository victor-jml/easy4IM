package pers.enoch.im.api.service;

/**
 * @Author yang.zhao
 * 2020/12/21
 * sms service
 **/
public interface SmsService {

    /**
     * send sms code
     * @param mobile user phone number
     */
    void sendSmsCode(String mobile);

    /**
     * check code
     * @param mobile user phone number
     * @param code sms code
     * @return boolean true or false
     */
    boolean verifyCode(String mobile,String code);
}
