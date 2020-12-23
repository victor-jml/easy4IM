package pers.enoch.im.api.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description:
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
