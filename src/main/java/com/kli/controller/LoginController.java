package com.kli.controller;

import com.kli.pojo.Emp;
import com.kli.pojo.Result;
import com.kli.service.EmpService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class LoginController {
    @Resource
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        if(emp == null){
            log.error("参数不能为空");
            return Result.error("参数不能为空");
        }
        if(StringUtils.isBlank(emp.getUsername())){
            log.error("用户名不能为空");
            return Result.error("用户名不能为空");
        }
        if(StringUtils.isBlank(emp.getPassword())){
            log.error("密码不能为空");
            return Result.error("密码不能为空");
        }
        Emp result = empService.login(emp.getUsername(), emp.getPassword());
        if(result == null){
            log.warn("账号或密码错误");
            return Result.error("账号或密码错误");
        }
        return Result.success();
    }
}