package pers.enoch.im.common.vo.res;

import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * @Date 2020/12/11 14:50
 * @Version 1.0
 * @Description
 **/
@Data
@Builder
public class UserLoginResVO {
    private String userId;

    private String token;

    private Long timestamp;
}
