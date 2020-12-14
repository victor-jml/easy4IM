package pers.enoch.im.common.utils;

import lombok.Data;
import pers.enoch.im.common.constant.ResultEnum;

import java.io.Serializable;

/**
 * @Author yang.zhao
 * @Date 2020/12/11 14:30
 * @Version 1.0
 * @Description
 **/
@Data
public class Result implements Serializable {
    
    private Integer code;
    
    private String message;

    private Object data;

    public Result(){
        super();
    }

    public Result(ResultEnum resultEnum,Object data){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public static Result success(){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result failure(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public static Result failure(ResultEnum resultEnum,Object data){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(data);
        return result;
    }
}
