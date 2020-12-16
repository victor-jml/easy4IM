package pers.enoch.im.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author yang.zhao
 * @Date 2020/12/16 17:12
 * @Version 1.0
 * @Description 用户信息
 **/
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
    private String name;

    /**
     * 头像
     */
    private String icon;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户状态（0-正常，1-禁用，2-删除）
     */
    private Integer status;

    /**
     * 个性签名
     */
    private String signature;

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

    public Users() {
    }
}
