package com.kli.mapper;

import com.kli.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmpMapper {
    List<Emp> query(Emp emp);

    void insert(Emp emp);

    void update(Emp emp);


    void delete(Integer id);

    Emp queryById(Integer id);
}
