package com.kli.mapper;

import com.kli.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> query(Dept dept);
    void insert(Dept dept);

    void update(Dept dept);

    void delete(Integer id);

    Dept queryById(Integer id);

    @Select("select * from dept where name = #{name}")
    Dept queryByName(String name);
}
