package com.kli.service;

import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.qo.EmpQO;

import java.util.List;

public interface EmpService {
    boolean insert(Emp emp);

    boolean delete(Integer id);

    void update(Emp emp);

    PageBean queryWithPage(EmpQO empQO);

    PageBean list(Integer page, Integer pageSize);
}
