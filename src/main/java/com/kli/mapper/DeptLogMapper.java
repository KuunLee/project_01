package com.kli.mapper;

import com.kli.dbo.DeptLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    void insert(DeptLog deptLog);
}
