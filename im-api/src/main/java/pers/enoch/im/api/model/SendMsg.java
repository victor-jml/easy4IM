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
    @TableField(value = "sender_id")
    private String senderId;

    /**
     * 接收者id
     */
    @TableField(value = "receiver_id")
    private String receiverId;

    /**
     * 消息接受类型(0-私聊,1-群聊)
     */
    @TableField(value = "msg_type")
    private Integer msgType;

    /**
     * 消息类型(0-文字,1-图片，2-语音)
     */
    @TableField(value = "content_type")
    private Integer contentType;

    /**
     * 消息内容
     */
    @TableField(value = "msg_content")
    private String msgContent;

    /**
     * 服务端收到消息的时间
     */
    @TableField(value = "send_time")
    private Date sendTime;

    /**
     * 消息是否送达
     */
    @TableField(value = "delivered")
    private Integer delivered;

    /**
     * tcp包头seq
     */
    @TableField(value = "msg_seq")
    private Integer msgSeq;


}
