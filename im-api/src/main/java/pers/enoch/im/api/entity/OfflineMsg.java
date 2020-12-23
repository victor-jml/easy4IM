package pers.enoch.im.api.entity;

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
public class OfflineMsg extends Model<OfflineMsg> {
    /**
     * 消息id
     */
    private Long msgId;

    /**
     * 发送者id
     */
    private String msgFrom;

    /**
     * 接收者id
     */
    private String msgTo;

    /**
     * 群id(当群聊的情况下)
     */
    private Long groupId;

    /**
     * tcp包头命令号
     */
    private Integer cmdId;

    /**
     * tcp包头seq
     */
    private Integer msgSeq;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 服务端收到消息的时间
     */
    private Date sendTime;

    /**
     * 0-未送达，1-送达
     */
    private Integer delivered;

}
