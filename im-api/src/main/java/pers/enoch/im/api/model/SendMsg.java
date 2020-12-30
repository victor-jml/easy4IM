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
@TableName(value = "tb_send_msg")
public class SendMsg extends Model<SendMsg> {
    /**
     * 消息id
     */
    @TableId(value = "msg_id",type = IdType.AUTO)
    private Long msgId;

    /**
     * 发送者id
     */
    @TableField(value = "msg_from")
    private String msgFrom;

    /**
     * 接收者id
     */
    @TableField(value = "msg_to")
    private String msgTo;

    /**
     * 群id(当群聊的情况下)
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * tcp包头命令号
     */
    @TableField(value = "cmd_id")
    private Integer cmdId;

    /**
     * tcp包头seq
     */
    @TableField(value = "msg_seq")
    private Integer msgSeq;

    /**
     * 消息内容
     */
    @TableField(value = "msg_content")
    private String msgContent;

    /**
     * 服务端收到消息的时间
     */
    @TableField(value = "msg_time")
    private Date sendTime;

    /**
     * 消息类型(0-文字,1-图片，2-语音)
     */
    @TableField(value = "msg_type")
    private Integer msgType;

}
