package com.kli.mapper;

import com.kli.dbo.Student;
import com.kli.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StudentMapper {
    List<StudentVO> queryList(String name, Short gender, Short classRank, Short grade,
                              LocalDate begin, LocalDate end, String headTeacherName);

    void deleteByIds(List<Integer> ids);

    void update(Student student);

    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student queryById(Integer id);
}
