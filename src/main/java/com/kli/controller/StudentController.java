package com.kli.controller;

import com.kli.pojo.PageBean;
import com.kli.pojo.Result;
import com.kli.pojo.Student;
import com.kli.service.StudentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Resource
    private StudentService studentService;

    @PostMapping
    public Result save(@RequestBody Student student){
        //参数校验
        if(student == null){
            log.error("数据为空：{}",student);
            return Result.error("数据不能为空");
        }
        else if(StringUtils.isBlank(student.getName())){
            log.error("姓名不能为空：{}",student);
            return Result.error("姓名不能为空");
        }
        else if(StringUtils.isBlank(student.getGender().toString())){
            log.error("性别不能为空：{}",student);
            return Result.error("性别不能为空");
        }
        else if(StringUtils.isBlank(student.getGrade().toString())){
            log.error("年级不能为空：{}",student);
            return Result.error("年级不能为空");
        }
        else if(StringUtils.isBlank(student.getClazz().toString())){
            log.error("班级不能为空：{}",student);
            return Result.error("班级不能为空");
        }
        studentService.insert(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        boolean res = studentService.delete(id);
        if(!res){
            log.error("删除失败");
            return Result.error("删除失败");
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    @GetMapping
    public Result list(String name, Short gender, Short grade,
                       Short clazz, String headTeacherName,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean res = studentService.list(name,gender,grade,clazz,headTeacherName,begin,end,page,pageSize);
        return Result.success(res);
    }
}
