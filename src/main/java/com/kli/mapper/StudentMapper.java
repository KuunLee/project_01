package com.kli.mapper;

import com.kli.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> queryList(String name, Short gender, Short grade, Short clazz,
                            LocalDate begin,LocalDate end,String headTeacherName);

    void deleteByIds(List<Integer> ids);

    void update(Student student);

    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student queryById(Integer id);
}
