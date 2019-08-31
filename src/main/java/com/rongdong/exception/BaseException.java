package com.rongdong.exception;

/**
 * @author hsh
 * @create 2018-04-02 16:38
 **/
public class BaseException extends Exception {
    private static final long serialVersionUID = 1539844079803958641L;

    public BaseException(String message) {
        super(message);
    }
}
