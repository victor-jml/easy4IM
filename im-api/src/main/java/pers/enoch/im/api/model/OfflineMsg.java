package pers.enoch.im.api.model;

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
@TableName(value = "tb_offline_msg")
public class OfflineMsg extends Model<OfflineMsg> {
    /**
     * 消息id
     */
    @TableId(value = "msg_id",type = IdType.AUTO)
    private Long msgId;

    /**
     * 发送者id
     */
    @TableField(value = "")
    private String msgFrom;

    /**
     * 接收者id
     */
    @TableField(value = "")
    private String msgTo;

    /**
     * 群id(当群聊的情况下)
     */
    @TableField(value = "")
    private Long groupId;

    /**
     * tcp包头命令号
     */
    @TableField(value = "")
    private Integer cmdId;

    /**
     * tcp包头seq
     */
    @TableField(value = "")
    private Integer msgSeq;

    /**
     * 消息内容
     */
    @TableField(value = "")
    private String msgContent;

    /**
     * 服务端收到消息的时间
     */
    @TableField(value = "")
    private Date sendTime;

    /**
     * 0-未送达，1-送达
     */
    @TableField(value = "")
    private Integer delivered;

    /**
     * 消息类型(0-文字,1-图片，2-语音)
     */
    @TableField(value = "msg_type")
    private Integer msgType;

}
