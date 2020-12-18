package pers.enoch.im.common.vo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 11:07
 * @Version 1.0
 * @Description 用户密码登录
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPwdLoginReqVO extends BaseRequestVO{

    /**
     * 通过userId进行登录
     */
    private String userId;

    /**
     * 通过email进行登录
     */
    private String email;

    /**
     * 通过手机号进行登录
     */
    private String phone;


    @NotEmpty(message = "密码不能为空")
    private String password;
}
