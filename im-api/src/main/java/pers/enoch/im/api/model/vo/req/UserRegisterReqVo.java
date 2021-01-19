package pers.enoch.im.api.model.vo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author yang.zhao
 * 2020/12/18
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegisterReqVo extends BaseRequestVo {

    @NotNull(message = "电话号码不可为空")
    private String phone;

    @NotNull(message = "用户id不可为空")
    private String userId;

    @NotNull(message = "密码不可为空")
    private String password;

}
