package pers.enoch.im.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author yang.zhao
 * @Date 2020/12/14 15:10
 * @Version 1.0
 * @Description
 **/
@Getter
@Setter
public class GenericException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer errorCode;
    String errorMessage;

    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Exception oriEx) {
        super(oriEx);
    }

    public GenericException(Exception oriEx, String message) {
        super(message, oriEx);
    }

    public GenericException(Throwable oriEx) {
        super(oriEx);
    }

    public GenericException(String message, Exception oriEx) {
        super(message, oriEx);
    }

    public GenericException(String message, Throwable oriEx) {
        super(message, oriEx);
    }


}
