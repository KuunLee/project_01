package com.kli;

import com.kli.mapper.EmpMapper;
import com.kli.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

//    @Test
//    public void testListEmp(){
//        List<Emp> emps = empMapper.listEmp();
//        emps.forEach(System.out::println);
//    }

//    @Test
//    public void testQuery(){
//        List<Emp> emps = empMapper.query();
//        emps.forEach(System.out::println);
//    }
}
