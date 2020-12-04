package pers.enoch.im.common.vo.res;

import lombok.Data;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 11:20
 * @Version 1.0
 * @Description 基础响应消息
 **/
@Data
public class BaseResVO<T> {

    /**
     * 返回的code（200...）
     */
    private Integer code;

    /**
     * 通知消息
     */
    private String message;

    /**
     * 实体类
     */
    private T data;
}
