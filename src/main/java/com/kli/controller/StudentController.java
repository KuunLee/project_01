package com.kli.controller;

import com.kli.dbo.PageBean;
import com.kli.dbo.Result;
import com.kli.dbo.Student;
import com.kli.service.StudentService;
import com.kli.vo.StudentVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Resource
    private StudentService studentService;

    @PostMapping
    public Result save(@RequestBody StudentVO student){
        //参数校验
        if(student == null){
            log.error("数据为空：{}",student);
            return Result.error("数据不能为空");
        }
        else if(StringUtils.isBlank(student.getName())){
            log.error("姓名不能为空：{}",student);
            return Result.error("姓名不能为空");
        }
        else if(student.getGender() == null){
            log.error("性别不能为空：{}",student);
            return Result.error("性别不能为空");
        }
        else if(student.getGrade() == null){
            log.error("班级不能为空：{}",student);
            return Result.error("班级不能为空");
        }
        else if(student.getClassRank() == null){
            log.error("年级不能为空：{}",student);
            return Result.error("年级不能为空");
        }
        boolean res = studentService.insert(student);
        if(!res){
            log.error("班级不存在,参数为：{}",student);
            return Result.error("班级不存在");
        }
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        boolean res = studentService.deleteByIds(ids);
        if(!res){
            log.error("删除失败");
            return Result.error("删除失败");
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody StudentVO student){
        boolean res = studentService.update(student);
        if(!res){
            log.error("班级不存在,参数为：{}",student);
            return Result.error("班级不存在");
        }
        return Result.success();
    }

    @GetMapping
    public Result list(String name, Short gender, Short grade,
                       Short classRank, String headTeacherName,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean res = studentService.list(name,gender,grade,classRank,headTeacherName,begin,end,page,pageSize);
        return Result.success(res);
    }

    @GetMapping("/{id}")
    public Result queryInfoById(@PathVariable Integer id){
        StudentVO student = studentService.queryById(id);
        return Result.success(student);
    }
}
