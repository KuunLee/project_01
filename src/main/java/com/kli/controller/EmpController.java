package com.kli.controller;

import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.pojo.Result;
import com.kli.qo.EmpQO;
import com.kli.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result insert(@RequestBody Emp emp){
        if(emp == null){
            log.error("参数不能为空");
            return Result.error("参数不能为空");
        } else if (StringUtils.isBlank(emp.getUsername())) {
            log.error("用户名不能为空");
            return Result.error("用户名不能为空");
        } else if (StringUtils.isBlank(emp.getName())) {
            log.error("姓名不能为空");
            return Result.error("姓名不能为空");
        } else if (emp.getGender() == null || StringUtils.equals(emp.getGender().toString(),"")) {
            log.error("性别不能为空");
            return Result.error("性别不能为空");
        }
        boolean result = empService.insert(emp);
        if(!result){
            log.warn("数据已存在，不执行插入操作");
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        boolean flat = empService.delete(id);
        if(!flat){
            log.warn("数据不存在，未进行删除操作");
            return Result.error("数据不存在，未进行删除操作");
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        if(emp == null){
            log.error("参数不能为空");
            return Result.error("参数不能为空");
        } else if (emp.getId() == null || StringUtils.equals(emp.getId().toString(),"")) {
            log.error("id不能为空");
            return Result.error("id不能为空");
        } else if (StringUtils.isBlank(emp.getUsername())) {
            log.error("用户名不能为空");
            return Result.error("用户名不能为空");
        } else if (StringUtils.isBlank(emp.getName())) {
            log.error("姓名不能为空");
            return Result.error("姓名不能为空");
        } else if (emp.getGender() == null || StringUtils.equals(emp.getGender().toString(),"")) {
            log.error("性别不能为空");
            return Result.error("性别不能为空");
        }
        empService.update(emp);
        return Result.success();
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
//        if (empQO == null){
//            log.error("参数不能为空");
//        }
//        log.info("开始查询分页数据：{},{},{}",empQO.getEmp(),empQO.getPage(),empQO.getPageSize());
//        PageBean PageBean = empService.queryWithPage(empQO);
//        return Result.success(PageBean);

        log.info("开始查询分页数据：{},{}",page,pageSize);
        PageBean pageBean = empService.list(page,pageSize);
        return Result.success(pageBean);
    }
}
