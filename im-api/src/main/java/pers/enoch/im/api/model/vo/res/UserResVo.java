package pers.enoch.im.api.model.vo.res;

import lombok.Builder;
import lombok.Data;
import pers.enoch.im.api.model.Users;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description:
 **/
@Data
@Builder
public class UserResVo extends Users {
    /**
     * login success return token
     */
    private String token;

    /**
     * login success timestamp
     */
    private Long timestamp;

}
