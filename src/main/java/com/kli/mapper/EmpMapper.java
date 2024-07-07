package com.kli.mapper;

import com.kli.pojo.Emp;
import com.kli.qo.EmpQO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    List<Emp> query(Emp emp);

    void insert(Emp emp);

    void update(Emp emp);

    @Delete("delete from emp where id = #{id}")
    void delete(Integer id);

    @Select("select * from emp where id = #{id}")
    Emp queryById(Integer id);

    List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);

    void deleteByIds(List<Integer> ids);

    List<Emp> queryPaging(EmpQO empQO);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp queryByUsernameAndPassword(String username, String password);

    /**
     * 根据部门id删除员工
     * @param deptId
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
