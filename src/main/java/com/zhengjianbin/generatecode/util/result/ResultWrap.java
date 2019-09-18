package com.zhengjianbin.generatecode.util.result;

import com.github.pagehelper.Page;

/**
 * Created by zhengjianbin on 2019/7/31.
 */
public class ResultWrap {

    /**
     * Instantiates a new base mapper.
     */
    private ResultWrap() {
    }

    /**
     * Wrap.
     *
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @param message
     *            the message
     * @param o
     *            the o
     * @return the wrapper
     */
    public static <E> Result<E> wrap(int code, String message, E o, Page<E> page) {
        return new Result<E>(code, message, o, page);
    }

    /**
     * Wrap.
     *
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @param message
     *            the message
     * @param o
     *            the o
     * @return the wrapper
     */
    public static <E> Result<E> wrap(int code, String message, E o) {
        return new Result<E>(code, message, o);
    }

    /**
     * Wrap.
     *
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @param message
     *            the message
     * @return the wrapper
     */
    public static <E> Result<E> wrap(int code, String message) {
        return new Result<E>(code, message);
    }

    /**
     * Wrap.
     *
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @return the wrapper< e>
     */
    public static <E> Result<E> wrap(int code) {
        return wrap(code, null);
    }

    /**
     * Wrap.
     *
     * @param <E>
     *            the element type
     * @param e
     *            the e
     * @return the wrapper
     */
    public static <E> Result<E> wrap(Exception e) {
        return new Result<E>(Result.ERROR_CODE, e.getMessage());
    }

    /**
     * Un wrapper.
     *
     * @param <E>
     *            the element type
     * @param wrapper
     *            the wrapper
     * @return the e
     */
    public static <E> E unWrap(Result<E> wrapper) {
        return wrapper.getResult();
    }

    /**
     * Wrap ERROR. code=100
     *
     * @param <E>
     *            the element type
     * @return the wrapper< e>
     */
    public static <E> Result<E> illegalArgument() {
        return wrap(Result.ILLEGAL_ARGUMENT_CODE_, Result.ILLEGAL_ARGUMENT_MESSAGE);
    }

    /**
     * Wrap ERROR. code=500
     *
     * @param <E>
     *            the element type
     * @return the wrapper< e>
     */
    public static <E> Result<E> error() {
        return wrap(Result.ERROR_CODE, Result.ERROR_MESSAGE);
    }

    /**
     * Wrap SUCCESS. code=200
     *
     * @param <E>
     *            the element type
     * @return the wrapper< e>
     */
    public static <E> Result<E> ok() {
        return new Result<E>();
    }

}
