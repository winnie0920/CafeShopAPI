package com.cafeshop.Result;

import lombok.Data;

import java.util.Objects;

/**
 * 后端统一返回结果
 */
@Data
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    // success
    public static Result success(Object data,String msg) {
        Result r = new Result();
        r.code = ResultCode.SUCCESS.getCode();
        r.msg = Objects.requireNonNullElse(msg, "success");
        r.data = data;
        return r;
    }

    public static Result success(Object data) {
        return success(data, "success");
    }

    public static Result success() {
        return success(null);
    }

    // error（重點）
    public static Result error(ResultCode code, String msg) {
        Result r = new Result();
        r.code = code.getCode();
        r.msg = msg;
        return r;
    }

    public static Result error(ResultCode code) {
        return error(code, "error");
    }
}