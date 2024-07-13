package com.kli.mapper;

import com.kli.dbo.Emp;
import org.apache.ibatis.annotations.Mapper;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TestMapper {

    List<Emp> queryEmps(String name, Short gender, LocalDate startTime,LocalDate endTime);
}
