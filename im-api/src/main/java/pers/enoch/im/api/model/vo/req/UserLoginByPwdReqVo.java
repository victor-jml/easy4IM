package pers.enoch.im.api.model.vo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 11:07
 * @Version 1.0
 * @Description user login by password
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginByPwdReqVo extends BaseRequestVo {

    /**
     * 通过userId进行登录
     */
    private String userId;

    /**
     * 通过email进行登录
     */
    private String email;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
