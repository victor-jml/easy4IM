package pers.enoch.im.common.vo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 11:07
 * @Version 1.0
 * @Description 用户密码登录
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPwdLoginReqVO extends BaseRequestVO{


    @NotNull(message = "用户id不能为空")
    private String userId;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
