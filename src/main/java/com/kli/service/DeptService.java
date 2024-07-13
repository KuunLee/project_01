package com.kli.service;

import com.kli.dbo.Dept;
import java.util.List;

public interface DeptService {
    boolean insert(Dept dept);

    boolean delete(Integer id);

    boolean update(Dept dept);

    List<Dept> query(Dept dept);

    Dept queryById(Integer id);
}
