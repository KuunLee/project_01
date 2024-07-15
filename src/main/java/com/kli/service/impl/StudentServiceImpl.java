package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.dbo.Clazz;
import com.kli.mapper.ClazzMapper;
import com.kli.mapper.StudentMapper;
import com.kli.dbo.PageBean;
import com.kli.dbo.Student;
import com.kli.service.StudentService;
import com.kli.vo.StudentVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClazzMapper clazzMapper;
    @Override
    public PageBean list(String name, Short gender, Short grade,
                         Short classRank, String headTeacherName, LocalDate begin,LocalDate end,
                              Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<StudentVO> studentVOList = studentMapper.queryList(name, gender, classRank,grade,
                begin,end,headTeacherName);

        PageBean pageBean = new PageBean();

        if(CollectionUtils.isEmpty(studentVOList)){
            pageBean.setTotal((long)0);
            pageBean.setRows(new ArrayList());
        }

        if(CollectionUtils.isEmpty(studentVOList)){
            pageBean.setTotal((long)0);
            pageBean.setRows(studentVOList);
        }

        Page res = (Page) studentVOList;
        pageBean.setTotal(res.getTotal());
        pageBean.setRows(res.getResult());
        return pageBean;
    }


    @Override
    public boolean deleteByIds(List<Integer> ids) {
        try {
            studentMapper.deleteByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(StudentVO student) {
        //判断班级是否存在
        List<Clazz> clazz = clazzMapper.queryByClassGrade(student.getClassRank(), student.getGrade());
        if(CollectionUtils.isEmpty(clazz)) return false;

        Student stu = new Student();
        stu.setName(student.getName());
        stu.setGender(student.getGender());
        stu.setAdmissionDate(student.getAdmissionDate());
        stu.setUpdateTime(LocalDateTime.now());
        stu.setId(student.getId());
        stu.setClassId(clazz.get(0).getId());
        studentMapper.update(stu);
        return true;
    }

    @Override
    public boolean insert(StudentVO student) {
        Student stu = new Student();
        //检查班级是否存在
        List<Clazz> classes = clazzMapper.queryByClassGrade(student.getClassRank(), student.getGrade());
        if(CollectionUtils.isEmpty(classes)) return false;

        stu.setName(student.getName());
        stu.setGender(student.getGender());
        stu.setAdmissionDate(student.getAdmissionDate());
        stu.setCreateTime(LocalDateTime.now());
        stu.setUpdateTime(LocalDateTime.now());
        stu.setClassId(classes.get(0).getId());
        studentMapper.insert(stu);
        return true;
    }

    @Override
    public StudentVO queryById(Integer id) {
        Student student = studentMapper.queryById(id);
        if(student == null) return null;
        //查询班级的信息
        Clazz clazz = clazzMapper.queryById(student.getClassId());
        if(clazz == null){
            log.error("通过班级id未查询到班级信息，班级id为：{}",student.getClassId());
            return null;
        }

        //将DBO对象转换为VO对象
        StudentVO studentVO = new StudentVO();
        studentVO.setId(id);
        studentVO.setName(student.getName());
        studentVO.setGender(student.getGender());
        studentVO.setAdmissionDate(student.getAdmissionDate());
        studentVO.setClassRank(clazz.getClassRank());
        studentVO.setGrade(clazz.getGrade());
        studentVO.setCreateTime(student.getCreateTime());
        studentVO.setUpdateTime(student.getUpdateTime());
        return studentVO;
    }

    private List<StudentVO> transferStudent(List<Student> students) {
        //转换为StudentVO对象
        List<StudentVO> studentVOList = new ArrayList<>();
        students.forEach(student -> {
            StudentVO studentVO = new StudentVO();
            Clazz clazz = clazzMapper.queryById(student.getClassId());
            studentVO.setId(student.getId());
            studentVO.setName(student.getName());
            studentVO.setGender(student.getGender());
            studentVO.setAdmissionDate(student.getAdmissionDate());
            studentVO.setCreateTime(student.getCreateTime());
            studentVO.setUpdateTime(student.getUpdateTime());
            studentVO.setClassRank(clazz.getClassRank());
            studentVO.setGrade(clazz.getGrade());
            studentVOList.add(studentVO);
        });

        return studentVOList;
    }
}
