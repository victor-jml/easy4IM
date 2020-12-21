package pers.enoch.im.common.model;

import lombok.Builder;
import lombok.Data;

/**
 * 2020-12-21
 * @author yang.zhao
 */
@Data
@Builder
public class LocalAuth {


    /**
     * 自增id
     */
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话号码
     */
    private String userPhone;

}
