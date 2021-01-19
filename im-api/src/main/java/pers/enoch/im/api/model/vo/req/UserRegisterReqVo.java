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

    private String phone;

    private String userId;

    private String password;

}
