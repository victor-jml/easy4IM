package pers.enoch.im.common.model.vo.res;

import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description:
 **/
@Data
@Builder
public class UserResVo {
    /**
     * login success return token
     */
    private String token;

    /**
     * login success timestamp
     */
    private Long timestamp;

}
