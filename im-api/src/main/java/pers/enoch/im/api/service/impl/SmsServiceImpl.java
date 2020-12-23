//package pers.enoch.im.api.service.impl;
//
//import com.tencentcloudapi.sms.v20190711.models.SendStatus;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import pers.enoch.im.api.service.SmsService;
//import pers.enoch.im.common.config.SmsConfig;
//import pers.enoch.im.common.constant.Constant;
//import pers.enoch.im.common.constant.SmsLengthEnum;
//import pers.enoch.im.common.utils.RedisUtil;
//import pers.enoch.im.common.utils.SmsCodeUtil;
//import pers.enoch.im.common.utils.SmsUtil;
//
///**
// * @Author yang.zhao
// * 2020/12/21
// **/
//@Service
//@Slf4j
//public class SmsServiceImpl implements SmsService {
////    private SmsConfig smsConfig = new SmsConfig();
//
//    @Value("${sms.templateIds.code}")
//    private String templateIdCode;
//
////    @Autowired
////    public SmsServiceImpl(SmsConfig smsConfig){
////        this.smsConfig = smsConfig;
////    }
//
//
//    @Override
//    public void sendSmsCode(String mobile) {
//        // 验证是否已保存的验证码
//        Object o = RedisUtil.get(smsConfig.getPhonePrefix(), mobile);
//        String[] templateParams = {};
//        String code = "";
//        if(o != null){
//            code = (String)o;
//            templateParams = new String[]{code,
//                    smsConfig.getExpireTime()};
//        }else {
//            code = SmsCodeUtil.createSmsRandomCode(SmsLengthEnum.SMS_LENGTH_6);
//           templateParams = new String[]{code,
//           smsConfig.getExpireTime()};
//        }
//        String[] mobiles = {Constant.MOBILE_CHINA_PREFIX + mobile};
//        SendStatus[] sendStatuses = SmsUtil.sendMessage(smsConfig, templateIdCode, templateParams, mobiles);
//        // 发送成功则保存验证码
//        if("Ok".equals(sendStatuses[0].getCode())){
//            RedisUtil.set(smsConfig.getPhonePrefix(),mobile,code,Long.parseLong(smsConfig.getExpireTime()));
//        }else {
//
//        }
//
//    }
//
//    @Override
//    public boolean verifyCode(String mobile, String code) {
//        return false;
//    }
//}
