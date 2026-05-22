package com.cafeshop.exception;

import com.cafeshop.Result.ResultCode;

public class BusinessException extends RuntimeException {
    private final ResultCode code;
    public BusinessException(ResultCode code, String msg) {
        super(msg);
        this.code = code;
    }
    public ResultCode getCode() {
        return code;
    }
}