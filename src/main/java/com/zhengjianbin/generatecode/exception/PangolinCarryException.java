package com.zhengjianbin.generatecode.exception;

/**
 * Created by zhengjianbin on 2019/9/12.
 */
public class PangolinCarryException extends RuntimeException {

    private static final long serialVersionUID = 1401593546385403720L;
    private Integer errorCode;

    public PangolinCarryException() {
        super();
    }

    public PangolinCarryException(String message) {
        super(message);
    }

    public PangolinCarryException(Throwable cause) {
        super(cause);
    }

    public PangolinCarryException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public PangolinCarryException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public PangolinCarryException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PangolinCarryException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

}
