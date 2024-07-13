package com.kli.service;

import com.kli.dbo.PageBean;
import com.kli.dbo.Student;
import com.kli.vo.StudentVO;

import java.time.LocalDate;
import java.util.List;


public interface StudentService {
    PageBean list(String name, Short gender, Short grade,Short classRank,
                  String headTeacherName, LocalDate begin,LocalDate end,
                  Integer page, Integer pageSize);

    boolean deleteByIds(List<Integer> ids);

    boolean update(StudentVO student);

    boolean insert(StudentVO student);

    StudentVO queryById(Integer id);
}
