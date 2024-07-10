package com.kli.service;

import com.kli.pojo.PageBean;
import com.kli.pojo.Student;

import java.time.LocalDate;


public interface StudentService {
    PageBean list(String name, Short gender, Short grade,Short clazz,
                  String headTeacherName, LocalDate begin,LocalDate end,
                  Integer page, Integer pageSize);

    boolean delete(Integer id);

    void update(Student student);

    void insert(Student student);
}
