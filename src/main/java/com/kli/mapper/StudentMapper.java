package com.kli.mapper;

import com.kli.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> queryList(String name, Short gender, Short grade, Short clazz,
                            LocalDate begin,LocalDate end,String headTeacherName);

    @Delete("delete from student where id = #{id}")
    void delete(Integer id);

    void update(Student student);

    void insert(Student student);
}
