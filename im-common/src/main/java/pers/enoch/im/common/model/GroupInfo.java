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
public class GroupInfo extends Model<GroupInfo> {

    /**
     * 自增主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 群主ID
     */
    @TableField(value = "own_id")
    private String ownId;

    /**
     * 群名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 群描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 群头像
     */
    @TableField(value = "group_icon")
    private String groupIcon;

    /**
     * 建立时间
     */
    @TableField(value = "create_at")
    private Date createAt = new Date();

    /**
     * 更新时间
     */
    @TableField(value = "update_at")
    private Date updateAt;
}
