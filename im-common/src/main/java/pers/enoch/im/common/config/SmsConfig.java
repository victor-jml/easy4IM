package pers.enoch.im.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yang.zhao
 * 2020/12/21
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {
    /** 腾讯云账户密钥对secretId（在访问管理中配置） */
    private String secretId;

    /** 腾讯云账户密钥对secretKey（在访问管理中配置） */
    private String secretKey;

    /** 短信应用appId */
    private String appId;

    /** 短信应用appKey */
    private String appKey;

    /** 签名 */
    private String smsSign;

    /** 过期时间 */
    private String expireTime;

    /** redis存储前缀 */
    private String phonePrefix;

}
