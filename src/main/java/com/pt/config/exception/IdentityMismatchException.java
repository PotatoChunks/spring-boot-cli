package com.pt.config.exception;

/**
 * 身份不匹配异常
 */
public class IdentityMismatchException  extends RuntimeException {

    public IdentityMismatchException(String message) {
        super(message);
    }

    public IdentityMismatchException(Throwable cause) {
        super(cause);
    }

    public IdentityMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}