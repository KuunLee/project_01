package com.kli.exception;

import com.kli.dbo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("系统异常，请联系管理员");
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public Result ex(IllegalArgumentException e){
//        e.printStackTrace();
//        return Result.error(e.getMessage());
//    }

}
