package com.kli.controller;

import com.kli.pojo.Dept;
import com.kli.pojo.Result;
import com.kli.qo.EmpQO;
import com.kli.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        if(dept == null){
            log.error("参数不能为空：{}",dept);
            return Result.error("参数不能为空");
        } else if (StringUtils.isBlank(dept.getName())) {
            log.error("部门名称不能为空,传递的参数为：{}",dept);
            return Result.error("部门名称不能为空");
        }
        boolean result = deptService.insert(dept);
        if(!result){
            log.warn("数据已存在，不执行插入操作，参数为：{}",dept);
            return Result.error("部门名称重复");
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        boolean flat = deptService.delete(id);
        if(!flat){
            log.warn("数据不存在，未进行删除操作");
            return Result.error("数据不存在，未进行删除操作");
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        if(dept == null){
            log.error("参数不能为空：{}",dept);
            return Result.error("参数不能为空");
        } else if (dept.getId() == null || StringUtils.equals(dept.getId().toString(),"")) {
            log.error("id不能为空,传递的参数为：{}",dept);
            return Result.error("id不能为空");
        } else if (StringUtils.isBlank(dept.getName())) {
            log.error("部门名称不能为空,传递的参数为：{}",dept);
            return Result.error("部门名称不能为空");
        }
        boolean result = deptService.update(dept);
        if(!result){
            log.error("部门名称重复,参数为：{}",dept);
            return Result.error("部门名称重复");
        }
        return Result.success();
    }

    @GetMapping
    public Result list(Dept dept){
        List<Dept> depts = deptService.query(dept);
        return Result.success(depts);
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        Dept dept = deptService.queryById(id);
        return Result.success(dept);
    }
}
