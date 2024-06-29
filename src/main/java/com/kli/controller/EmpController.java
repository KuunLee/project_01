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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result save(@RequestBody Emp emp){
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
        log.info("新增员工，信息为：{}",emp);
        boolean result = empService.save(emp);
        if(!result){
            log.warn("数据已存在，不执行插入操作");
        }
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作开始，id参数为：{}",ids);
        if (CollectionUtils.isEmpty(ids)){
            log.error("参数有误，请检查，参数为：{}",ids);
            return Result.error("id参数为空，请检查");
        }
        empService.deleteByIds(ids);
//        if(!flat){
//            log.warn("数据不存在，未进行删除操作");
//            return Result.error("数据不存在，未进行删除操作");
//        }
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
        boolean result = empService.update(emp);
        if(!result){
            log.error("用户名重复，请检查：{}",emp.getUsername());
            return Result.error("用户名重复");
        }
        return Result.success();
    }

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        if (empQO == null){
//            log.error("参数不能为空");
//        }
//        log.info("开始查询分页数据：{},{},{}",empQO.getEmp(),empQO.getPage(),empQO.getPageSize());
//        PageBean PageBean = empService.queryWithPage(empQO);
//        return Result.success(PageBean);
        log.info("开始查询分页数据：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @GetMapping("/{id}")
    public Result queryInfoById(@PathVariable Integer id){
        log.info("开始查询员工详情信息,id：{}",id);
        Emp emp = empService.queryInfoById(id);
        if(emp == null){
            return Result.error("未查询到数据，请检查参数");
        }
        return Result.success(emp);
    }
}
