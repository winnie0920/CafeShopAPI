package com.cafeshop.Result;

public enum ResultCode {

    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    TOKEN_INVALID(9004),
    SYSTEM_ERROR(500);

    private final Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}