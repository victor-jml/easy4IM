package pers.enoch.im.api.model.vo.res;

import lombok.*;
import pers.enoch.im.api.model.UserInfo;

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
