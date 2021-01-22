package pers.enoch.im.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author yang.zhao
 * Date: 2021/1/19
 * Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName(value = "tb_third_part_auth")
public class ThirdPartAuth extends Model<ThirdPartAuth> {
    /**
     * 自增主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * oauth_name
     */
    @TableField("oauth_name")
    private String oauthName;

    /**
     * oauth_id
     */
    @TableField("oauth_id")
    private String oauthId;

    /**
     * 第三方认证token
     */
    @TableField("oauth_token")
    private String oauthToken;

    /**
     * oauth_expire
     */
    @TableField("oauth_expire")
    private String oauthExpire;
}
