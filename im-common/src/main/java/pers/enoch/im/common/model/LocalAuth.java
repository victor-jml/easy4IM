package pers.enoch.im.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("tb_local_auth")
public class LocalAuth extends Model<LocalAuth> {

    /**
     * 用户id
     */
    @TableId("user_id")
    private String userId;

    /**
     * 邮箱
     */
    @TableField("user_email")
    private String userEmail;

    /**
     * 密码
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 电话号码
     */
    @TableField("user_phone")
    private String userPhone;
}
