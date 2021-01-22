package pers.enoch.im.common.model;

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
 * Date: 2021/1/19
 * Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("tb_group_info")
public class GroupMember extends Model<GroupMember> {

    /**
     * 自增主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 群ID
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;


    /**
     * 建立时间
     */
    @TableField(value = "create_at")
    private Date createAt = new Date();

}
