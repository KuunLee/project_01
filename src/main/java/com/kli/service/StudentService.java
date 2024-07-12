package com.kli.service;

import com.kli.pojo.PageBean;
import com.kli.pojo.Student;

import java.time.LocalDate;
import java.util.List;


public interface StudentService {
    PageBean list(String name, Short gender, Short grade,Short clazz,
                  String headTeacherName, LocalDate begin,LocalDate end,
                  Integer page, Integer pageSize);

    boolean deleteByIds(List<Integer> ids);

    void update(Student student);

    void insert(Student student);

    Student queryById(Integer id);
}
