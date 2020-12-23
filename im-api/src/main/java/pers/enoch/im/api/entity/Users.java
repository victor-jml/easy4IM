package pers.enoch.im.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("tb_users")
public class Users extends Model<Users> {

    /**
     * 自增id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 昵称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 头像
     */
    @TableField("user_icon")
    private String userIcon;

    /**
     * 年龄
     */
    @TableField("user_age")
    private Integer userAge;

    /**
     * 用户状态（0-正常，1-禁用，2-删除）
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
     * 个性签名
     */
    @TableField("user_signature")
    private String userSignature;

    /**
     * 在线状态
     */
    @TableField("online")
    private Integer online;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @TableField("update_at")
    private Date updateAt;

    /**
     * 上次登录时间戳
     */
    @TableField("last_login")
    private Long lastLogin;

    /**
     * 上次登出时间戳
     */
    @TableField("last_logout")
    private Long lastLogout;


}

