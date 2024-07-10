package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.mapper.StudentMapper;
import com.kli.pojo.PageBean;
import com.kli.pojo.Student;
import com.kli.service.StudentService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public PageBean list(String name, Short gender, Short grade,
                         Short clazz, String headTeacherName, LocalDate begin,LocalDate end,
                              Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Student> students = studentMapper.queryList(name, gender, grade,clazz,
                begin,end,headTeacherName);

        PageBean pageBean = new PageBean();
        if(CollectionUtils.isEmpty(students)){
            pageBean.setTotal((long)0);
            pageBean.setRows(students);
        }
        Page res = (Page) students;
        pageBean.setTotal(res.getTotal());
        pageBean.setRows(res.getResult());
        return pageBean;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            studentMapper.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void insert(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }
}
