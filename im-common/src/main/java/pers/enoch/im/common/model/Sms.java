package pers.enoch.im.common.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * 2020/12/21
 * 短信参数实体
 **/
@Data
@Builder
public class Sms {
    /**
     * 签名
     */
    private String sign ;
    /**
     * 模板
     */
    private String templateId;
    /**
     * 手机号
     */
    private String[] mobile;
    /**
     * 模板参数 {}
     */
    private String[] params;
}
