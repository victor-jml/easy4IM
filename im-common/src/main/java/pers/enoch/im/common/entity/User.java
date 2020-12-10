package pers.enoch.im.common.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 16:00
 * @Version 1.0
 * @Description user实体类
 **/
@Builder
@Data
public class User {
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 个性签名
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifiedTime;

}
