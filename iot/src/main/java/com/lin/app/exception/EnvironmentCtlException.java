package com.lin.app.exception;

/**
 * EnvironmentCtlException
 * @author linzhongsheng
 */
public class EnvironmentCtlException extends RuntimeException {

    public EnvironmentCtlException() {
    }

    public EnvironmentCtlException(String message) {
        super(message);
    }

    public EnvironmentCtlException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnvironmentCtlException(String message, String code, Throwable cause) {
        super(message, cause);
    }

    public EnvironmentCtlException(Throwable cause) {
        super(cause);
    }

    public EnvironmentCtlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
