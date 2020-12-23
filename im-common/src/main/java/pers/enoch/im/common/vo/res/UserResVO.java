package pers.enoch.im.common.vo.res;

import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description:
 **/
@Data
@Builder
public class UserResVO {
    /**
     * 成功登录（注册）返回Token
     */
    private String token;

    /**
     * 时间戳
     */
    private Long timestamp;
}
