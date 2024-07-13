package com.kli.mapper;

import com.kli.dbo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    void insert(Clazz clazz);

    void deleteByIds(List<Integer> ids);

    void update(Clazz clazz);

    List<Clazz> query(Short classRank, String headTeacherName);

    @Select("select * from clazz where id = #{id}")
    Clazz queryById(Integer id);

    List<Clazz> queryByClassGrade(Short classRank, Short grade);

     List<Clazz> queryByIds(List<Integer> ids);
}
