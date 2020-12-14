package pers.enoch.im.common.exception;

import pers.enoch.im.common.constant.ResultEnum;

/**
 * @Author yang.zhao
 * @Date 2020/12/14 15:09
 * @Version 1.0
 * @Description
 **/
public class IMException extends GenericException{
    public IMException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public IMException(Exception e, int errorCode, String errorMessage) {
        super(e, errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public IMException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public IMException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.errorMessage = resultEnum.getMessage();
        this.errorCode = resultEnum.getCode();
    }

    public IMException(ResultEnum resultEnum, String message) {
        super(message);
        this.errorMessage = message;
        this.errorCode = resultEnum.getCode();
    }

    public IMException(Exception oriEx) {
        super(oriEx);
    }

    public IMException(Throwable oriEx) {
        super(oriEx);
    }

    public IMException(String message, Exception oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }

    public IMException(String message, Throwable oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }


    public static boolean isResetByPeer(String msg) {
        if ("Connection reset by peer".equals(msg)) {
            return true;
        }
        return false;
    }
}
