package com.kli.service;

import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    boolean save(Emp emp);

    void deleteByIds(List<Integer> ids);

    boolean update(Emp emp);

    List<Emp> query(Emp emp);

    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin,LocalDate end);

    Emp queryInfoById(Integer id);
}
