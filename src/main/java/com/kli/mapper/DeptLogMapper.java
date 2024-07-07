package com.kli.mapper;

import com.kli.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface DeptLogMapper {

    void insert(DeptLog deptLog);
}
