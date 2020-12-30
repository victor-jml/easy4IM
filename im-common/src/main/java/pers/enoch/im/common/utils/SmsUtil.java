package pers.enoch.im.common.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import pers.enoch.im.common.config.SmsConfig;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.exception.IMException;

/**
 * @Author yang.zhao
 * 2020/12/21
 * 发送短信工具类
 **/
public class SmsUtil {

    /**
     * 腾讯云发送短信的基础服务
     *
     * @param smsConfig  腾讯云短信服务配置
     * @param templateId     腾讯云短信模板id
     * @param templateParams 腾讯云模板需要的参数
     * @param mobiles   手机号集合
     * @return 发送短信后的状态的set集合
     */
    public static SendStatus[] sendMessage(SmsConfig smsConfig, String templateId,
                                           String[] templateParams,String[] mobiles){
        Credential cred = new Credential(smsConfig.getSecretId(), smsConfig.getSecretKey());
        /*
          实例化要请求产品(以sms为例)的client对象
          第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量
         */
        SmsClient client = new SmsClient(cred, "");
        /*
          实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
         */
        SendSmsRequest req = new SendSmsRequest();
        //短信应用ID: 短信appId在 [短信控制台] 添加应用后生成的实际SdkAppid，示例如1400006666
        req.setSmsSdkAppid(smsConfig.getAppId());
        //短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看
        req.setSign(smsConfig.getSmsSign());
        //模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看
        req.setTemplateID(templateId);
        //下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]  示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号
        req.setPhoneNumberSet(mobiles);
        //模板参数: 若无模板参数，则设置为空
        req.setTemplateParamSet(templateParams);

        /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
           返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应
         */
        SendSmsResponse res = null;
        try {
            //发送短信
            res = client.SendSms(req);
            return res.getSendStatusSet();
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            //发送短信失败
            throw new IMException(ResultEnum.SMS_SEND_ERROR);
        }
    }
}
