package com.kli.controller;

import com.kli.dbo.Clazz;
import com.kli.dbo.PageBean;
import com.kli.dbo.Result;
import com.kli.service.clazzService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/classes")
public class ClazzController {
    @Resource
    private clazzService clazzService;
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        if(clazz == null){
            log.error("参数为空，请检查参数：{}",clazz);
            return Result.error("参数为空");
        } else if (clazz.getClassRank() == null) {
            log.error("年级为空，请检查：{}",clazz);
            return Result.error("年级为空");
        } else if (clazz.getGrade() == null) {
            log.error("班级为空，请检查：{}",clazz);
            return Result.error("班级为空");
        } else if (clazz.getHeadTeacherName() == null) {
            log.error("班主任名称为空，请检查：{}",clazz);
            return Result.error("班主任名称为空");
        }
        boolean res = clazzService.insert(clazz);
        if(!res){
            log.error("班级重复，未执行新增操作：{}",clazz);
            return Result.error("班级重复");
        }
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)){
            log.error("參數为空，请检查：{}",ids);
            return Result.error("参数为空");
        }
        clazzService.deleteByIds(ids);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        if(clazz == null){
            log.error("参数为空，请检查参数：{}",clazz);
            return Result.error("参数为空");
        } else if (clazz.getId() == null) {
            log.error("id为空，请检查：{}",clazz);
            return Result.error("id为空");
        } else if (clazz.getClassRank() == null) {
            log.error("年级为空，请检查：{}",clazz);
            return Result.error("年级为空");
        } else if (clazz.getGrade() == null) {
            log.error("班级为空，请检查：{}",clazz);
            return Result.error("班级为空");
        } else if (clazz.getHeadTeacherName() == null) {
            log.error("班主任名称为空，请检查：{}",clazz);
            return Result.error("班主任名称为空");
        }
        boolean res = clazzService.update(clazz);
        if(!res){
            log.error("班级重复，未执行修改操作：{}",clazz);
            return Result.error("班级重复");
        }
        return Result.success();
    }

    @GetMapping
    public Result list(Short classRank,String headTeacherName,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean res = clazzService.list(classRank,headTeacherName,page,pageSize);
        return Result.success(res);
    }

    @GetMapping("/{id}")
    public Result queryInfoById(@PathVariable Integer id){
        if(id == null){
            log.error("参数为空");
            return Result.error("参数有误");
        }
        Clazz clazz = clazzService.queryInfoById(id);
        return Result.success(clazz);
    }
}
