package com.kli.exception;

import com.kli.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("操作失败了，请联系管理员");
    }
}
