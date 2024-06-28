package com.kli.mapper;

import com.kli.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> query(Dept dept);
    void insert(Dept dept);

    void update(Dept dept);

    void delete(Integer id);

    Dept queryById(Integer id);
}
