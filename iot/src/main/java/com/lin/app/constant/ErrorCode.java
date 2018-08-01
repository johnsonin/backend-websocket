package com.lin.app.constant;

/**
 * @author ping
 */
public enum ErrorCode {

    ILLEGAL_ARGUMENT (400, "ILLEGAL_ARGUMENT", "Illegal Argument"),
    NOT_FOUND        (404, "NOT_FOUND",        "Not Found"),
    SYSTEM_ERROR     (500, "SYSTEM_ERROR",     "System Error"),
    ;

    private int code;
    private String name;
    private String message;

    ErrorCode(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
