package com.cafeshop.exception;

import com.cafeshop.Result.Result;
import com.cafeshop.Result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result handleBusiness(BusinessException e) {
        log.warn("BusinessException: {}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicate(DuplicateKeyException e) {
        log.error("DuplicateKeyException", e);
        return Result.error(ResultCode.BAD_REQUEST, "資料已存在");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("System error", e);
        return Result.error(ResultCode.SYSTEM_ERROR, "系統錯誤，請聯繫管理員");
    }
}