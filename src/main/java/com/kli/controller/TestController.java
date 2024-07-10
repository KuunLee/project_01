package com.kli.controller;

import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.pojo.Result;
import com.kli.service.EmpService;
import com.kli.service.TestService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
