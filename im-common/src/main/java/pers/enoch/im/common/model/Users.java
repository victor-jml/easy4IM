package pers.enoch.im.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 2020-12-21
 * @author yang.zhao
 *
 */
@Data
@Builder
public class Users {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 头像
     */
    private String userIcon;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 用户状态（0-正常，1-禁用，2-删除）
     */
    private Integer userStatus;

    /**
     * 个性签名
     */
    private String userSignature;

    /**
     * 在线状态
     */
    private Integer online;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    /**
     * 上次登录时间戳
     */
    private Long lastLogin;

    /**
     * 上次登出时间戳
     */
    private Long lastLogout;


}
