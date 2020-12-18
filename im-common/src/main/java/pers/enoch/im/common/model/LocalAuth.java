package pers.enoch.im.common.model;


import lombok.Builder;
import lombok.Data;

/**
 * @Author yang.zhao
 * @Date 2020/12/16 17:14
 * @Version 1.0
 * @Description
 **/

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
     * 用户邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 昵称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}

