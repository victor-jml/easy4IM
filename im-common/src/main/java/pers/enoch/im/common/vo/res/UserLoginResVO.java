package pers.enoch.im.common.vo.res;

import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 11:15
 * @Version 1.0
 * @Description 用户登录后返回token
 **/
@Data
@Builder
public class UserLoginResVO {

    private String userId;

    private String token;
}
