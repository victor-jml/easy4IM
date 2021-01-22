package pers.enoch.im.common.model.vo.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 10:56
 * @Version 1.0
 * @Description HTTP请求
 **/

@ToString
@Getter
@Setter
public class BaseRequestVo {
    /**
     * 唯一请求编号
     */
    private String reqNo;

    /**
     * 请求时间戳
     */
    private Long timestamp;

    public BaseRequestVo(){
        reqNo = UUID.randomUUID().toString();
        timestamp = System.currentTimeMillis();
    }
}
