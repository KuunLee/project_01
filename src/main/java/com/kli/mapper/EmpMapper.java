package com.kli.mapper;

import com.kli.pojo.Emp;
import com.kli.qo.EmpQO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Mapper
public interface EmpMapper {
    List<Emp> query(Emp emp);

    void insert(Emp emp);

    void update(Emp emp);


    void delete(Integer id);

    Emp queryById(Integer id);

    List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);

    void deleteByIds(List<Integer> ids);

    List<Emp> queryPaging(EmpQO empQO);
}
