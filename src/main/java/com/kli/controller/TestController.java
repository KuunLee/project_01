package com.kli.controller;

import com.kli.dbo.PageBean;
import com.kli.dbo.Result;
import com.kli.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping
    public Result list(String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){

        PageBean query = testService.list(name,gender,startTime,endTime,page,pageSize);
        return Result.success(query);
    }
}
